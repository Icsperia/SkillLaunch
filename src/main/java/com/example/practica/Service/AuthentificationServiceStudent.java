package com.example.practica.Service;

import com.example.practica.Auth.AuthentificationRequestStudent;
import com.example.practica.Auth.AuthentificationResponseStudent;
import com.example.practica.Auth.RegisterRequestStudent;
import com.example.practica.Config.JwtService;
import com.example.practica.Entity.Role;
import com.example.practica.Entity.Student;
import com.example.practica.Repo.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthentificationServiceStudent {
private final StudentRepo studentRepo;
private final PasswordEncoder passwordEncoder;
private final JwtService jwtService;
private final AuthenticationManager authenticationManager;
private final TokenStudentService tokenStudentService;

    public AuthentificationResponseStudent register(RegisterRequestStudent request) {

        var student = Student.builder()

                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .facultate(request.getFaculty())
                .universitate(request.getUniversity())
                .role(Role.STUDENT)
                .build();

        if (studentRepo.existsByEmail(request.getEmail())) {
            return new AuthentificationResponseStudent("N-am idee", "");
        } else {
            Student savedStudent = studentRepo.save(student);

            var jwtToken = jwtService.generateToken(savedStudent);
            var studentToken = tokenStudentService.createTokenStudent(student.getEmail());
            return AuthentificationResponseStudent.builder()

                    .token(jwtToken)
                    .tokenStudent(studentToken.getTokenStudent())
                    .build();
        }
    }



    public AuthentificationResponseStudent authenticate(AuthentificationRequestStudent request) {

authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        )
);
var student = studentRepo.findByEmail(request.getEmail()).orElseThrow(null);


        var jwtToken = jwtService.generateToken(student);
        var studentToken= tokenStudentService.createTokenStudent(student.getEmail());
        return  AuthentificationResponseStudent.builder()

                .token(jwtToken)
                .tokenStudent(studentToken.getTokenStudent())
                .build();


    }
}
