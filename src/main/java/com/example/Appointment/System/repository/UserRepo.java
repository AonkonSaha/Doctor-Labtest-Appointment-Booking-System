package com.example.Appointment.System.repository;

import com.example.Appointment.System.model.entity.MUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<MUser,Long> {
    Optional<MUser> findByName(String username);

    Optional<MUser> findByContact(String contact);

    boolean existsByContact(String contact);
}
