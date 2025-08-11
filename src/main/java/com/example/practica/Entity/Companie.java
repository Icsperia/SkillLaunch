package com.example.practica.Entity;

import com.example.practica.UUID.GeneratedUuidV7;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Builder
@Getter
@Setter
@Entity
@Table(name="Companie", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email_companie"}),
        @UniqueConstraint(columnNames = {"parola"}),
        @UniqueConstraint(columnNames = {"nume_companie"})
})

@NoArgsConstructor
@AllArgsConstructor
public class Companie implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;


@Column(nullable = false, unique = true)
    private String name;
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    private String password;
    private String description;
    private String fieldOfActivity;

    private String reviews;




    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToOne(mappedBy = "companie")
    private TokenCompanie companieToken;



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



