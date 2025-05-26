package com.example.Appointment.System.controller;

import com.example.Appointment.System.jwt.utils.JwtUtils;
import com.example.Appointment.System.jwt.utils.RequestUtils;
import com.example.Appointment.System.model.dto.LoginDTO;
import com.example.Appointment.System.model.dto.PasswordDTO;
import com.example.Appointment.System.model.dto.UserDTO;
import com.example.Appointment.System.model.entity.MUser;
import com.example.Appointment.System.model.mapper.UserMapper;
import com.example.Appointment.System.service.UserService;
import com.example.Appointment.System.service.UserValidationService;
import com.example.Appointment.System.service.ValidationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserAuthController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final JwtUtils jwtUtils;
    private final UserValidationService userValidationService;
    private final ValidationService validationService;
    @GetMapping("/")
    public String startPage(HttpServletRequest request){
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            return "redirect:/home.html";
        }
        return "redirect:/login";
    }
    @GetMapping("/register")
    public String registerUser(){
       return "register";
    }
    @GetMapping("/login")
    public String loginUser(){
        return "login";
    }
    @GetMapping("/home")
    @ResponseBody
    public ResponseEntity<UserDTO> getHome(){
        return ResponseEntity.ok(userMapper.toUserDTO(userService.findUserByContact(SecurityContextHolder.getContext().getAuthentication().getName())));
    }
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO){
        if(!validationService.validatePatientDetails(userDTO).isEmpty()){
            return ResponseEntity.badRequest().body(validationService.validatePatientDetails(userDTO));
        }
        return ResponseEntity.ok(
                userMapper.toUserDTO(userService.saveUser(userMapper.toUser(userDTO)))
        );
    }
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO){
        MUser user= userService.findUserByContact(loginDTO.getContact());
        if(user==null){
            return ResponseEntity.badRequest().body("User doesn't exit..");
        }
        if(!userValidationService.isExitUserPassword(loginDTO.getContact(),loginDTO.getPassword())){
            return ResponseEntity.badRequest().body("Password is incorrect..");
        }
        return ResponseEntity.ok(Map.of("token",userService.authenticateUser(user,loginDTO)));
    }
    @PostMapping("/signout")
    @ResponseBody
    public ResponseEntity<?> logoutUser(){
        HttpServletRequest request= RequestUtils.getCurrentHttpRequest();
        if(request==null){
            return ResponseEntity.badRequest().body("HttpServletRequest Object is empty");
        }
        String token = request.getHeader("Authorization").substring(7);
        String contact=jwtUtils.extractContact(token);
        userService.logoutUser(contact);
        return ResponseEntity.ok("Logout successfully");
    }

    @PostMapping("/update/profile")
    @ResponseBody
    public ResponseEntity<?> updateProfileWithOutPassword(
            @RequestParam String fullName,
            @RequestParam String email,
            @RequestParam String dob,
            @RequestParam String gender,
            @RequestParam(required = false) MultipartFile photo
    ) throws IOException {
       if(!userValidationService.isValidGender(gender)){
           return ResponseEntity.badRequest().body("Gender must be male,female or other");
       }
       if(!userValidationService.isValidEmailFormat(email)){
           return ResponseEntity.badRequest().body("Email must contain @");
       }
       return ResponseEntity.ok(
                userMapper.toUserDTO(userService.updatePatientWithOutPassword(
                        fullName,email,dob,gender,photo)));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteUserById(@PathVariable Long id){
        if(!userValidationService.isExitUserById(id)){
            return ResponseEntity.badRequest().body("User doesn't exit!");
        }
        userService.deleteUser(id);
         return ResponseEntity.ok("Deleted successfully");
    }
    @GetMapping("/fetch/user")
    @ResponseBody
    public ResponseEntity<?> fetchUser(){
        String contact=SecurityContextHolder.getContext().getAuthentication().getName();
        if(!userValidationService.isExitUserByContact(contact)){
            return ResponseEntity.badRequest().body("User doesn't exit..");
        }
        return ResponseEntity.ok(userMapper.toUserDTO(userService.findUserByContact(contact)));
    }

    @PostMapping("/user/change-password")
    @ResponseBody
    public ResponseEntity<?> updateUserPassword(@RequestBody PasswordDTO passwordDTO){
        String contact=SecurityContextHolder.getContext().getAuthentication().getName();
        if(!userValidationService.isExitUserByContact(contact)){
            return ResponseEntity.badRequest().body("User doesn't exit..");
        }
        if(!validationService.validatePatientPasswordUpdate(passwordDTO).isEmpty()){
            return ResponseEntity.badRequest().body(validationService.validatePatientPasswordUpdate(passwordDTO));
        }
        userService.updateUserPassword(contact,passwordDTO);
        return ResponseEntity.ok("Password updated successfully");
    }


}
