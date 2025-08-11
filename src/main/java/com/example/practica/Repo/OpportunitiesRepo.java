package com.example.practica.Repo;

import com.example.practica.Entity.Opportunities ;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpportunitiesRepo extends JpaRepository<Opportunities, Long> {

   List<Opportunities> findByType(String type);
   List<Opportunities> findByLocation(String location);

}
