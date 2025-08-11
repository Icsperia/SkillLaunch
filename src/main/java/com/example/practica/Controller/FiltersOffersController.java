package com.example.practica.Controller;

import com.example.practica.DTO.OpportunitiesDto;
import com.example.practica.Entity.Opportunities;
import com.example.practica.Mapper.OpportunitiesMapper;
import com.example.practica.Service.OpportunitiesService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("offers/filters")
//@PreAuthorize("hasRole('STUDENT')")
@AllArgsConstructor
public class FiltersOffersController {

    private final OpportunitiesService opportunitiesService;
    private final OpportunitiesMapper opportunitiesMapper;


    @GetMapping("/findByType/{type}")
    public List<OpportunitiesDto> findByType(@PathVariable String type) {
        List<Opportunities> offersList = opportunitiesService.findByType(type);
        return offersList.stream().map(opportunitiesMapper::toDto).toList();
    }

    @GetMapping("/findByLocation/{location}")
    public List<OpportunitiesDto> findByLocation(@PathVariable String location) {
        List<Opportunities > offersList = opportunitiesService.findbyLocation(location);
        return offersList.stream().map(opportunitiesMapper::toDto).toList();
    }

    @GetMapping("/findAll")
    public List<OpportunitiesDto> findAll(Opportunities  opportunities) {
        List<Opportunities > offersList = opportunitiesService.findAll();
        return offersList.stream().map(opportunitiesMapper::toDto).toList();
    }

}
