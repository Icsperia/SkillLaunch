package com.example.practica.Repo;

import com.example.practica.Entity.Companie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Component
@Repository
public interface CompanieRepo  extends JpaRepository<Companie, String> {

Optional<Companie> findByEmail(String email);
Boolean existsByEmail(String email);
Optional<Companie> findById(Long id);


}
