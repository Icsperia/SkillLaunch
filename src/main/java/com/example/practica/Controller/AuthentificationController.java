package com.example.practica.Controller;

import com.example.practica.Auth.*;
import com.example.practica.Mapper.CompanieMapper;
import com.example.practica.Mapper.StudentMapper;
import com.example.practica.Repo.CompanieRepo;
import com.example.practica.Repo.StudentRepo;
import com.example.practica.Service.AuthentificationServiceCompanie;
import com.example.practica.Service.AuthentificationServiceStudent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthentificationController {


private final AuthentificationServiceStudent authentificationServiceStudent;
private final AuthentificationServiceCompanie authentificationServiceCompanie;

    @PostMapping("/register/student")
    public ResponseEntity<AuthentificationResponseStudent> register(@RequestBody RegisterRequestStudent request) {
        return ResponseEntity.ok(authentificationServiceStudent.register(request));
}

@PostMapping("/login/student")
    public ResponseEntity<AuthentificationResponseStudent> register(@RequestBody AuthentificationRequestStudent request) {
    return ResponseEntity.ok(authentificationServiceStudent.authenticate(request));
}




    @PostMapping("/register/companie")
    public ResponseEntity<AuthentificationResponseCompanie> register(@RequestBody RegisterRequestCompanie request) {
        return ResponseEntity.ok(authentificationServiceCompanie.register(request));
    }

    @PostMapping("/login/companie")
    public ResponseEntity<AuthentificationResponseCompanie> register(@RequestBody AuthentificationRequestCompanie request) {
        return ResponseEntity.ok(authentificationServiceCompanie.authenticate(request));
    }




}
