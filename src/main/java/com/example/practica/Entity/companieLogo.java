package com.example.practica.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document

public class companieLogo {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private String id;
private String name;
}
