package com.example.Appointment.System.jwt.filter;

import com.example.Appointment.System.exception.UserNotFoundException;
import com.example.Appointment.System.jwt.utils.JwtUtils;
import com.example.Appointment.System.model.entity.MUser;
import com.example.Appointment.System.repository.UserRepo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.weaver.patterns.IToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;
    private final UserRepo userRepo;


    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("I am in Auth Filter");
        String authHeader = request.getHeader("Authorization");
        String requestURI = request.getRequestURI();

        System.out.println(requestURI);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);


        String contact = jwtUtils.extractContact(token);
        if (contact != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(contact);
            Optional<MUser> user = userRepo.findByContact(contact);
            if(user.isEmpty()) {
                throw new IllegalArgumentException("User doesn't exit..");
            }

            if (user.get().getIsActive() && jwtUtils.validateToken(token, contact)) {
                System.out.println("Token---------------------: "+ token);

                System.out.println("Inner auth filter");
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);

    }
}
