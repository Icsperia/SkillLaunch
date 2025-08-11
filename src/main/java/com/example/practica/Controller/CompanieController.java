package com.example.practica.Controller;

import com.example.practica.DTO.CompanieDto;
import com.example.practica.Mapper.CompanieMapper;
import com.example.practica.Entity.Companie;
import com.example.practica.Repo.CompanieRepo;
import com.example.practica.Service.CompanieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/companie")
//@PreAuthorize("hasRole('COMPANIE')")

@RequiredArgsConstructor
public class CompanieController {
private final CompanieService companieService;
private final CompanieMapper companieMapper;
    private final CompanieRepo companieRepo;


    @GetMapping("/{id}")
    public ResponseEntity<CompanieDto> findCompanieById(@PathVariable("id") Long id) {
       Companie companie = companieService.findById(id);
   return ResponseEntity.ok(companieMapper.toDto(companie));
    }

    @GetMapping("/companieDto/{id}")
    public ResponseEntity<CompanieDto> findCompanieByCompanieId(@PathVariable("id") Long id) {
        Companie companie = companieService.findById(id);
        return ResponseEntity.ok(companieMapper.toDto(companie));
    }
    
    @PutMapping("/companieUpdate/{id}")
    public ResponseEntity<CompanieDto> updateCompanie(@PathVariable("id") Long id ,
                                                      @RequestBody CompanieDto companieDto) {
try{
   CompanieDto updatedCompanie = companieService.updateCompanie(id, companieDto);
   return ResponseEntity.ok(updatedCompanie);
}catch(Exception e){
    return ResponseEntity.badRequest().build();
}

}


}
