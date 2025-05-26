package com.example.Appointment.System.service;

import com.example.Appointment.System.model.dto.LoginDTO;
import com.example.Appointment.System.model.dto.PasswordDTO;
import com.example.Appointment.System.model.dto.UserDTO;
import com.example.Appointment.System.model.entity.MUser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public interface UserService {

    MUser saveUser(MUser user);
    String authenticateUser(MUser user,LoginDTO loginDTO);
    MUser getUserByName(String username) ;
    MUser updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
    MUser getUserById(Long id);
    MUser findUserByContact(String contact);
    MUser updatePatientWithOutPassword(String fullName, String email, String dob, String gender, MultipartFile photo);
    void  updateUserPassword(String contact, PasswordDTO passwordDTO);
    void logoutUser(String contact);

}
