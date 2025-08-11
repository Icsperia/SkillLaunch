package com.example.practica.Controller;

import com.example.practica.DTO.StudentDto;
import com.example.practica.Mapper.StudentMapper;
import com.example.practica.Entity.Student;
import com.example.practica.Repo.StudentRepo;
import com.example.practica.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
//@PreAuthorize("hasRole('STUDENT')")

public class StudentController {

    private final StudentRepo studentRepo;
    private final StudentMapper studentMapper;
    private final StudentService studentService;



    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentRepo.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Student>> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(studentRepo.findById(id));
    }

    @GetMapping("/studentDto/{id}")
    public ResponseEntity<StudentDto> getStudentDto(@PathVariable("id") Long id) {
     Student student = studentService.findById(id);
       return ResponseEntity.ok(studentMapper.toDto(student));
    }
@PutMapping("/studentUpdate/{id}")
public ResponseEntity<StudentDto> updateStudent(@PathVariable("id") Long id, @RequestBody StudentDto studentDto) {
        try{
            StudentDto updateStudent= studentService.updateStudent(id,studentDto);
            return ResponseEntity.ok(updateStudent);
        }catch(Exception e){
          return ResponseEntity.badRequest().build();
            }
}

}
