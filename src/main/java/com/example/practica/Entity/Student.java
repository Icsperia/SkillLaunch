package com.example.practica.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Setter
@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor

@Table(name = "Student", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email_institutional"}),

})

public  class Student implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "prenume")
    private String firstName;

    @Column(name = "nume")
    private String lastName;
    @Column(name = "email_institutional")

    @Email
    @Pattern(regexp = "(?i)^[a-z0-9._%+-]+@(student|stud|stu|poli|poli\\.buc|uni)?\\.?[a-z0-9-]+\\.(edu\\.ro|ro)$")

    private String email;

    @Column(name = "parola")
    private String password;

    @Column(name = "facultate")
    private String facultate;

    @Column(name = "universitate")
    private String universitate;



    private String description;
    private String skills;
    private String experience;
    private String jobTitles;



    @OneToOne (mappedBy = "student")
    private TokenStudent tokenStudent;



    @Enumerated(EnumType.STRING)
    private Role role;




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }



    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}