package com.example.practica.Mapper;

import com.example.practica.DTO.StudentDto;
import com.example.practica.Entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public StudentDto toDto(Student student) {
        StudentDto studentDto = new StudentDto();

        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setEmail(student.getEmail());

        studentDto.setDescription(student.getDescription());
        studentDto.setEmail(student.getEmail());
        studentDto.setJobTitles(student.getJobTitles());
        studentDto.setSkills(student.getSkills());
        return studentDto;
    }

    public Student toEntity(StudentDto studentDto) {
        Student student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());
      
        student.setDescription(studentDto.getDescription());
        student.setJobTitles(studentDto.getJobTitles());
        student.setSkills(studentDto.getSkills());
        return student;
    }


}
