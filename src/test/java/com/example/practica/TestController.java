package com.example.practica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
public class TestController {
    @Autowired
    private DataSource dataSource;

    @GetMapping("/test-db")
    public String testDbConnection() {
        try (Connection conn = dataSource.getConnection()) {
            return "Conexiune reușită la baza de date!";
        } catch (SQLException e) {
            return "Eroare la conectare: " + e.getMessage();
        }
    }
}
