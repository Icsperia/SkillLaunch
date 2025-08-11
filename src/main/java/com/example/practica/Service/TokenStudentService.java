package com.example.practica.Service;

import com.example.practica.Entity.Student;
import com.example.practica.Entity.TokenStudent;
import com.example.practica.Repo.StudentRepo;
import com.example.practica.Repo.TokenStudentRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@Service
public class TokenStudentService
{
private final StudentRepo studentRepo;
private final TokenStudentRepo tokenStudentRepo;

public TokenStudent createTokenStudent(String email){
   Student student = studentRepo.findByEmail(email).orElseThrow(()->new RuntimeException("No such student"));
   TokenStudent tokenStudent=student.getTokenStudent();

   if(tokenStudent==null){
   tokenStudent=TokenStudent.builder()
            .tokenStudent(UUID.randomUUID().toString())
            .expires(Instant.now().plusMillis(60*60*60*1000))
        .student(studentRepo.findByEmail(email).orElseThrow(()->new RuntimeException("No such student")))
            .student(student)
            .build();
    tokenStudentRepo.save(tokenStudent);


}
return tokenStudent;

}




public TokenStudent verifyStudentToken(String tokenStudent){
   TokenStudent stToken= tokenStudentRepo.findByTokenStudent(tokenStudent).orElseThrow(()->new RuntimeException("No such token"));
   if(stToken.getExpires().compareTo(Instant.now())<0){
       tokenStudentRepo.delete(stToken);
       throw new RuntimeException("Token expired");
   }

return stToken;
}
}
