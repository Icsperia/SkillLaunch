package com.example.practica.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor

public class RegisterRequestCompanie {


private String name;
private String email;
private String password;



}
