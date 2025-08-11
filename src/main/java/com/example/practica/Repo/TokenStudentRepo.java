package com.example.practica.Repo;

import com.example.practica.Entity.TokenStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenStudentRepo extends JpaRepository<TokenStudent, Integer> {
    Optional<TokenStudent> findByTokenStudent(String  token);

}
