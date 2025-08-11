package com.example.practica.Mapper;

import com.example.practica.DTO.OpportunitiesDto;

import com.example.practica.Entity.Opportunities;
import org.springframework.stereotype.Component;

@Component
public class OpportunitiesMapper {

    public OpportunitiesDto toDto(Opportunities offers) {
        OpportunitiesDto offersDto = new OpportunitiesDto();
        offersDto.setDescription(offers.getDescription());
        offersDto.setLocation(offers.getLocation());
        offersDto.setType(offers.getType());
        offersDto.setSkills(offers.getSkills());
        offersDto.setStatus(offers.getStatus());
        return offersDto;

    }

}
