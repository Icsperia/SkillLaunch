package com.example.practica.Config;

import com.example.practica.Repo.CompanieRepo;
import com.example.practica.Repo.StudentRepo;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Service
public class ComposedUserDetails implements UserDetailsService {
    private final StudentRepo studentRepo;
    private final CompanieRepo companieRepo;
    private List<UserDetailsService> serviceList;


    public UserDetailsService userDetailsServiceStudents() {
        return username -> studentRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Nope"));
    }

    public UserDetailsService userDetailsServiceCompanies() {
        return username -> companieRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Nope2ND"));
    }
    @PostConstruct
    public void services() {
        List<UserDetailsService> services = new ArrayList<>();
        services.add(userDetailsServiceStudents());
        services.add(userDetailsServiceCompanies());
        this.serviceList = services;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        for (UserDetailsService service : serviceList) {
            try {
                return service.loadUserByUsername(email);
            }catch(UsernameNotFoundException ex){
                continue;
            }
        }
        throw new UsernameNotFoundException("Nope");
    }

}
