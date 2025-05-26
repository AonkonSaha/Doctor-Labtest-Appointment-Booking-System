package com.example.Appointment.System.service.Imp;

import com.example.Appointment.System.model.entity.MUser;
import com.example.Appointment.System.model.entity.UserRole;
import com.example.Appointment.System.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String contact) throws UsernameNotFoundException {
        Optional<MUser> user = userRepo.findByContact(contact);
        if(user.isEmpty()) {
            throw new UsernameNotFoundException("User not found...");
        }
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        for (UserRole userRole : user.get().getUserRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole.getRole()));
        }
        return new org.springframework.security.core.userdetails.User(
                user.get().getContact(),
                user.get().getPassword(),
                authorities
        );
    }
}
