package com.example.practica.Config;

import com.example.practica.Entity.Companie;
import com.example.practica.Entity.Student;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtAuthentificationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

private final ComposedUserDetails composedUserDetails;


    @Override
    protected void doFilterInternal
            (@NonNull HttpServletRequest request,
             @NonNull HttpServletResponse response,
             @NonNull FilterChain filterChain
            ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String tokenStudent;
        final String userEmailStudent;
        final String tokenCompanie;
        final String userEmailCompanie;


        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;


        }
        tokenStudent = authHeader.substring(7);
        userEmailStudent = jwtService.extractUsername(tokenStudent);
        if (userEmailStudent != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Student student = (Student) this.composedUserDetails.loadUserByUsername(userEmailStudent);
            if (jwtService.isTokenvalid(tokenStudent, student)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        student,
                        null,
                        student.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);

            }


        }

        tokenCompanie = authHeader.substring(7);
        userEmailCompanie = jwtService.extractUsername(tokenCompanie);
        if (userEmailCompanie != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                Companie companie = (Companie) this.composedUserDetails.loadUserByUsername(userEmailCompanie);
            if (jwtService.isTokenvalid(tokenCompanie, companie)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        companie,
                        null,
                       companie.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);

            }
            filterChain.doFilter(request, response);

        }

    }

    }














