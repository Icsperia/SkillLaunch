package com.example.practica.Mapper;

import com.example.practica.DTO.CompanieDto;
import com.example.practica.Entity.Companie;
import org.springframework.stereotype.Component;

@Component
public class CompanieMapper
{
   public CompanieDto toDto(Companie companie){
       CompanieDto companieDto = new CompanieDto();

       companieDto.setName(companie.getName());
       companieDto.setEmail(companie.getEmail());
       companieDto.setDescription(companie.getDescription());
       companieDto.setFieldOfActivity(companie.getFieldOfActivity());
       companieDto.setReviews(companie.getReviews());

       return companieDto;


   }
public Companie toEntity(CompanieDto companieDto){
       Companie companie = new Companie();

       companie.setName(companieDto.getName());
       companie.setEmail(companieDto.getEmail());
       companie.setDescription(companieDto.getDescription());
       companie.setFieldOfActivity(companieDto.getFieldOfActivity());
       companie.setReviews(companieDto.getReviews());
       return companie;
}

}
