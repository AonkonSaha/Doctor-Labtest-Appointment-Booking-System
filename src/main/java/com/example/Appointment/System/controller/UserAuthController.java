package com.example.Appointment.System.controller;

import com.example.Appointment.System.constant.ApiPaths;
import com.example.Appointment.System.exception.InvalidLoginArgumentException;
import com.example.Appointment.System.exception.InvalidUserArgumentException;
import com.example.Appointment.System.exception.LogoutArgumentException;
import com.example.Appointment.System.exception.UserNotFoundException;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@Tag(name = "User Auth API", description = "Handles user registration, login, and profile updates")
public class UserAuthController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final JwtUtils jwtUtils;
    private final UserValidationService userValidationService;
    private final ValidationService validationService;

    @GetMapping("/")
    public String startPage() {
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            return "redirect:/home.html";
        }
        return "redirect:" + ApiPaths.UserAuth.LOGIN;
    }

    @Operation(summary = "Returns current user's profile (Home Page)")
    @GetMapping(ApiPaths.UserAuth.HOME)
    @ResponseBody
    public ResponseEntity<UserDTO> getHome() {
        return ResponseEntity.ok(userMapper.toUserDTO(
                userService.findUserByContact(SecurityContextHolder.getContext().getAuthentication().getName())));
    }

    @Operation(summary = "Register a new user")
    @PostMapping(ApiPaths.UserAuth.REGISTER)
    @ResponseBody
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        var errors = validationService.validatePatientDetails(userDTO);
        if (!errors.isEmpty()) {
            throw new InvalidUserArgumentException(errors);
        }
        return ResponseEntity.ok(
                userMapper.toUserDTO(userService.saveUser(userMapper.toUser(userDTO)))
        );
    }

    @GetMapping(ApiPaths.UserAuth.REGISTER)
    public String registerUser() {
        return "register";
    }

    @Operation(summary = "Authenticate and login user")
    @PostMapping(ApiPaths.UserAuth.LOGIN)
    @ResponseBody
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody LoginDTO loginDTO) {
        MUser user = userService.findUserByContact(loginDTO.getContact());
        if (user == null) {
            throw new InvalidLoginArgumentException("User doesn't exist");
        }
        if (!userValidationService.isExitUserPassword(loginDTO.getContact(), loginDTO.getPassword())) {
            throw new InvalidLoginArgumentException("Password is incorrect");
        }
        return ResponseEntity.ok(Map.of("token", userService.authenticateUser(user, loginDTO)));
    }

    @GetMapping(ApiPaths.UserAuth.LOGIN)
    public String loginUser() {
        return "login";
    }

    @Operation(summary = "Logout the current user")
    @PostMapping(ApiPaths.UserAuth.LOGOUT)
    @ResponseBody
    public ResponseEntity<String> logoutUser() {
        HttpServletRequest request = RequestUtils.getCurrentHttpRequest();
        if (request == null) {
            throw new LogoutArgumentException("HttpServletRequest is empty");
        }
        String token = request.getHeader("Authorization").substring(7);
        String contact = jwtUtils.extractContact(token);
        userService.logoutUser(contact);
        return ResponseEntity.ok("Logout successfully");
    }

    @Operation(summary = "Update user profile without changing password")
    @PostMapping(ApiPaths.UserAuth.UPDATE_PROFILE)
    @ResponseBody
    public ResponseEntity<UserDTO> updateProfileWithOutPassword(
            @RequestParam String fullName,
            @RequestParam String email,
            @RequestParam String dob,
            @RequestParam String gender,
            @RequestParam(required = false) MultipartFile photo
    ) {
        if (!userValidationService.isValidGender(gender)) {
            throw new InvalidUserArgumentException("Gender must be male, female or other");
        }
        if (!userValidationService.isValidEmailFormat(email)) {
            throw new InvalidUserArgumentException("Email must contain @");
        }
        return ResponseEntity.ok(
                userMapper.toUserDTO(userService.updatePatientWithOutPassword(
                        fullName, email, dob, gender, photo)));
    }

    @Operation(summary = "Delete user by ID")
    @DeleteMapping(ApiPaths.UserAuth.DELETE)
    @ResponseBody
    public ResponseEntity<String> deleteUserById(
            @Parameter(description = "ID of the user to delete", example = "101")
            @PathVariable Long id) {
        if (!userValidationService.isExitUserById(id)) {
            throw new UserNotFoundException("User doesn't exist");
        }
        userService.deleteUser(id);
        return ResponseEntity.ok("Deleted successfully");
    }

    @Operation(summary = "Get the current user's profile")
    @GetMapping(ApiPaths.UserAuth.FETCH_USER)
    @ResponseBody
    public ResponseEntity<UserDTO> fetchUser() {
        String contact = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!userValidationService.isExitUserByContact(contact)) {
            throw new UserNotFoundException("User doesn't exist");
        }
        return ResponseEntity.ok(userMapper.toUserDTO(userService.findUserByContact(contact)));
    }

    @Operation(summary = "Update current user's password")
    @PostMapping(ApiPaths.UserAuth.UPDATE_PASSWORD)
    @ResponseBody
    public ResponseEntity<String> updateUserPassword(@RequestBody PasswordDTO passwordDTO) {
        String contact = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!userValidationService.isExitUserByContact(contact)) {
            throw new UserNotFoundException("User doesn't exist");
        }
        var errors = validationService.validatePatientPasswordUpdate(passwordDTO);
        if (!errors.isEmpty()) {
            throw new InvalidUserArgumentException(errors);
        }
        userService.updateUserPassword(contact, passwordDTO);
        return ResponseEntity.ok("Password updated successfully");
    }
}
