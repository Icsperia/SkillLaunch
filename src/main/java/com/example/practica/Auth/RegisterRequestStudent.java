package com.example.practica.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestStudent {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String faculty;
    private String university;



}
