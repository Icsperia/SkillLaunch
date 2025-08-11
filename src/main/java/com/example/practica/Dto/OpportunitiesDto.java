package com.example.practica.DTO;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpportunitiesDto {
    private String type;
    private String description;
    private String location;
    private String skills;
    private String status;

}
