package com.example.Appointment.System.config.security;


import com.example.Appointment.System.constant.ApiPaths;
import com.example.Appointment.System.jwt.filter.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity

public class SecurityConfiguration {
    @Autowired
    JwtAuthFilter jwtAuthFilter;
    public static final String[] ADMIN_URLS = {
            ApiPaths.UserRole.ROOT + "/**",
            ApiPaths.Doctor.ROOT+ApiPaths.Doctor.REGISTER,
            ApiPaths.Doctor.ROOT+ApiPaths.Doctor.UPDATE,
            ApiPaths.Doctor.ROOT+ApiPaths.Doctor.DELETE,
            ApiPaths.DiagnosticCenter.ROOT+ApiPaths.DiagnosticCenter.REGISTER,
            ApiPaths.DiagnosticCenter.ROOT+ApiPaths.DiagnosticCenter.DELETE,
            ApiPaths.DiagnosticCenter.ROOT+ApiPaths.DiagnosticCenter.UPDATE,
            ApiPaths.LabTest.ROOT+ApiPaths.LabTest.REGISTER,
            ApiPaths.LabTest.ROOT+ApiPaths.LabTest.DELETE,
            ApiPaths.LabTest.ROOT+ApiPaths.LabTest.UPDATE,
            ApiPaths.TimeSlot.ROOT+ApiPaths.TimeSlot.REGISTER,
            ApiPaths.TimeSlot.ROOT+ApiPaths.TimeSlot.UPDATE,
    };
    public static final String[] PATIENT_URLS = {
            ApiPaths.DoctorBooking.ROOT + "/**",
            ApiPaths.LabTestBooking.ROOT + "/**",
            ApiPaths.Doctor.ROOT+ApiPaths.Doctor.FETCH_ALL,
            ApiPaths.LabTest.ROOT+ApiPaths.LabTest.FETCH_ALL,
            ApiPaths.DiagnosticCenter.ROOT+ApiPaths.DiagnosticCenter.FETCH_ALL,
            ApiPaths.TimeSlot.ROOT+ApiPaths.TimeSlot.FETCH,
            ApiPaths.UserAuth.HOME,
            ApiPaths.UserAuth.FETCH_USER,
            ApiPaths.UserAuth.LOGOUT,
            ApiPaths.UserAuth.UPDATE_PROFILE,
            ApiPaths.UserAuth.UPDATE_PASSWORD
    };
    public static final String[] DOCTOR_URLS = {
//            "/api/doctor/**",
    };

    public static final String[] STATIC_RESOURCES = {
            "/css/**", "/js/**", "/images/**", "/favicon.ico"
    };

    public static final String[] HTML_PAGES = {
            "/home.html",
            "/login.html",
            "/register.html",
            "/profile.html",
            "/doctor/appointment.html",
            "/lab/test.html",
            "/history/history.html"
    };

    public static final String[] PUBLIC_URLS = {
            "/",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/login",
            "/register",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        System.out.println("I am in Security Filer Chain");
        http.
                csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PATIENT_URLS).hasAnyRole("PATIENT","ADMIN")
                        .requestMatchers(ADMIN_URLS).hasAnyRole("ADMIN")
                        .requestMatchers(DOCTOR_URLS).hasAnyRole("DOCTOR","ADMIN")
                        .requestMatchers(STATIC_RESOURCES).permitAll()
                        .requestMatchers(HTML_PAGES).permitAll()
                        .requestMatchers(PUBLIC_URLS).permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}
