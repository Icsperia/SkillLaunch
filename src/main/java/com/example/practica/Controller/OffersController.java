package com.example.practica.Controller;



import com.example.practica.DTO.OpportunitiesDto;
import com.example.practica.Entity.Companie;
import com.example.practica.Entity.Opportunities;

import com.example.practica.Mapper.OpportunitiesMapper;
import com.example.practica.Repo.CompanieRepo;

import com.example.practica.Repo.OpportunitiesRepo;
import com.example.practica.Service.CompanieService;

import com.example.practica.Service.OpportunitiesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/offers")
@RestController

public class OffersController {

private final OpportunitiesRepo opportunitiesRepo;
    private final CompanieRepo companieRepo;
 private final OpportunitiesService opportunitiesService;
private final OpportunitiesMapper opportunitiesMapper;

    @PostMapping("/postOffers/{companieId}")
    public ResponseEntity<Opportunities> saveOffers(@PathVariable Long companieId, @RequestBody Opportunities offers) {
        Companie companie = companieRepo.findById(companieId).orElseThrow();
        offers.setCompanie(companie);
        Opportunities savedOffers = opportunitiesRepo.save(offers);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOffers);
    }

    @PutMapping("/updateOffers/{companieId}/{offersId}")
    public ResponseEntity<Opportunities> updateOffers(@PathVariable Long offersId, @RequestBody Opportunities  offers) {
        try {
            Opportunities updateOffers = opportunitiesService.updateOpportunities(offersId, offers);
            return ResponseEntity.ok(updateOffers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{offersId}/{companieId}")
    public ResponseEntity<Opportunities > deleteOffers(@PathVariable Long offersId) {
       opportunitiesRepo.deleteById(offersId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    }