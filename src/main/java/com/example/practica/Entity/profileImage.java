package com.example.practica.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
@AllArgsConstructor
@Getter
@Setter
@Document
public class profileImage {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String image;


    public profileImage(String title) {
    this.image = title;
    }
}
