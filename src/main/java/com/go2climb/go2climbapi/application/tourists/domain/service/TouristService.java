package com.go2climb.go2climbapi.application.tourists.domain.service;

import com.go2climb.go2climbapi.application.tourists.domain.model.entity.Tourist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TouristService {

    List<Tourist> getAll();

    Page<Tourist> getAll(Pageable pageable);

    // Create a new user
    Tourist create(Tourist tourist);

    // Update user
    Tourist update(Long touristId, Tourist tourist);

    // Get info user by id
    Tourist getInfoUserById(Long touristId);

    // Get info user by Name
    Tourist getInfoUserByName(String name);

    //Get info by Email and Password
    Tourist getInfoUserByEmailAndPassword(String email, String password);

    ResponseEntity<?> delete(Long touristId);
}
