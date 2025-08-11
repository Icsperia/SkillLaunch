package com.example.practica.Repo;

import com.example.practica.Entity.profileImage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface profileImageRepo extends MongoRepository<profileImage, String> {
}
