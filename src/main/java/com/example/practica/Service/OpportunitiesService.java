package com.example.practica.Service;

import com.example.practica.Entity.Opportunities;
import com.example.practica.Repo.OpportunitiesRepo;
import com.example.practica.Repo.OpportunitiesRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OpportunitiesService {

    private final OpportunitiesRepo opportunitiesRepo;

    private Opportunities saveOffers(Opportunities opportunities){
        return opportunitiesRepo.save(opportunities);
    }

    public Opportunities updateOpportunities(Long id, Opportunities opportunities){
        Opportunities upOpportunities = opportunitiesRepo.findById(id).get();
        upOpportunities.setDescription(opportunities.getDescription());
        upOpportunities.setType(opportunities.getType());
        upOpportunities.setSkills(opportunities.getSkills());
        upOpportunities.setLocation(opportunities.getLocation());
        upOpportunities.setStatus(opportunities.getStatus());
        return opportunitiesRepo.save(upOpportunities);
    }
    private void deleteOffers( Long id){
        Opportunities opportunities = opportunitiesRepo.findById(id).get();
        opportunitiesRepo.delete(opportunities);

    }

    public List<Opportunities> findByType(String type){
        return opportunitiesRepo.findByType(type);
    }

    public List<Opportunities> findAll(){
        return opportunitiesRepo.findAll();
    }

    public List<Opportunities> findbyLocation(String location){
        return opportunitiesRepo.findByLocation(location);
    }
    /*
    public Offers findByType(String type) {
  return offersRepo.findByType(type);
    }
*/
}



