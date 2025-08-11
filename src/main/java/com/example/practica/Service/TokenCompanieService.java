package com.example.practica.Service;

import com.example.practica.Entity.Companie;
import com.example.practica.Entity.Student;
import com.example.practica.Entity.TokenCompanie;
import com.example.practica.Entity.TokenStudent;
import com.example.practica.Repo.CompanieRepo;
import com.example.practica.Repo.StudentRepo;
import com.example.practica.Repo.TokenCompanieRepo;
import com.example.practica.Repo.TokenStudentRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@Service
public class TokenCompanieService {
private final CompanieRepo companieRepo;

private final TokenCompanieRepo tokenCompanieRepo;

    public TokenCompanie createTokenCompanie(String email){
        Companie companie = companieRepo.findByEmail(email).orElseThrow(()->new RuntimeException("No such student"));
        TokenCompanie tokenCompanie =companie.getCompanieToken();

        if(tokenCompanie==null){
            tokenCompanie=TokenCompanie.builder()
                    .tokenCompanie(UUID.randomUUID().toString())
                    .expires(Instant.now().plusMillis(60*60*60*1000))
                    .companie(companieRepo.findByEmail(email).orElseThrow(()->new RuntimeException("No such student")))
                    .companie( companie)
                    .build();
            tokenCompanieRepo.save(tokenCompanie);


        }
        return tokenCompanie;

    }

    public TokenCompanie verifyStudentToken(String tokenStudent){
        TokenCompanie cmToken= tokenCompanieRepo.findByTokenCompanie(tokenStudent).orElseThrow(()->new RuntimeException("No such token"));
        if(cmToken.getExpires().compareTo(Instant.now())<0){
            tokenCompanieRepo.delete(cmToken);
            throw new RuntimeException("Token expired");
        }

        return cmToken;
    }
}

