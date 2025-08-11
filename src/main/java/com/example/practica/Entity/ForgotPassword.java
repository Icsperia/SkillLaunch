package com.example.practica.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ForgotPassword {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long otp;
    private Date expiryDate;
@OneToOne
    private Student student;


}
