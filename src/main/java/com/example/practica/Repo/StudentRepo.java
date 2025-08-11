package com.example.practica.Repo;

import com.example.practica.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Component
@Repository
public  interface StudentRepo extends JpaRepository<Student, Long> {

//Student findByEmail(String email);
Optional<Student> findByEmail( String email);
Boolean existsByEmail( String email);



}