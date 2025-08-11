package com.example.practica.Repo;

import com.example.practica.Entity.TokenCompanie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenCompanieRepo extends JpaRepository<TokenCompanie, Integer> {
Optional<TokenCompanie> findByTokenCompanie(String token);

}
