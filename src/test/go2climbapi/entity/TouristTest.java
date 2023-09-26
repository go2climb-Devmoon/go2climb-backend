
package com.go2climb.go2climbapi.entity;

import com.go2climb.go2climbapi.application.tourists.domain.model.entity.Tourist;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TouristTest {

    @Test
    @DisplayName("Test Tourist Getters and Setters")
    void Test(){
        Tourist tourist = new Tourist();
        tourist.setId(1L);
        tourist.setName("Giovani");
        tourist.setLastName("Black");
        tourist.setEmail("GiovaniBlack@gmail.com");
        tourist.setPassword("Black123");
        tourist.setPhoneNumber(999876123);
        tourist.setAddress("Industrial 24");
        tourist.setPhoto("perfil.jpg");

        assertEquals(1L, tourist.getId());
        assertEquals("Giovani", tourist.getName());
        assertEquals("Black", tourist.getLastName());
        assertEquals("GiovaniBlack@gmail.com", tourist.getEmail());
        assertEquals("Black123", tourist.getPassword());
        assertEquals(999876123, tourist.getPhoneNumber());
        assertEquals("Industrial 24", tourist.getAddress());
        assertEquals("perfil.jpg", tourist.getPhoto());
    }
}


