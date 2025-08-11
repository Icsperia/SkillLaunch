package com.example.practica.Service;

import com.example.practica.DTO.StudentDto;
import com.example.practica.Mapper.StudentMapper;
import com.example.practica.Entity.Student;
import com.example.practica.Repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {



    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private StudentMapper studentMapper;

    public List<Student> findAll() {
        return studentRepo.findAll();
    }


    public Student findById(Long id) {
        return studentRepo.findById(id).get();
    }


    public StudentDto updateStudent(Long id, StudentDto studentDto) {
        Student student = studentRepo.findById(id).get();
        student.setDescription(studentDto.getDescription());
        student.setExperience(studentDto.getExperience());
        student.setSkills(studentDto.getSkills());
        student.setJobTitles(studentDto.getJobTitles());
        Student updatedStudent = studentRepo.save(student);
        return studentMapper.toDto(updatedStudent);


    }
}
