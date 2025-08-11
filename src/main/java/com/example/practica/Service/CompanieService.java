package com.example.practica.Service;

import com.example.practica.DTO.CompanieDto;
import com.example.practica.Mapper.CompanieMapper;
import com.example.practica.Entity.Companie;
import com.example.practica.Repo.CompanieRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class CompanieService {

public final CompanieRepo companieRepo;
    private final CompanieMapper companieMapper;

    public  List<Companie> findAll() {
        return companieRepo.findAll();
    }

    public Companie findById(Long id) {
        return companieRepo.findById(id).get();
    }

    public CompanieDto updateCompanie(Long id, CompanieDto companieDto) {
       Companie companie = companieRepo.findById(id).get();
       companie.setDescription(companieDto.getDescription());
       companie.setFieldOfActivity(companieDto.getFieldOfActivity());
       Companie updatedCompanie = companieRepo.save(companie);
       return companieMapper.toDto(updatedCompanie);


}

}
