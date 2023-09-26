
package com.go2climb.go2climbapi.entity;

import com.go2climb.go2climbapi.application.agencies.domain.model.entity.Agency;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgencyTest {

    @Test
    @DisplayName("Test Agency Getters and Setters")
    void Test() {
        Agency agency = new Agency();
        agency.setId(1L);
        agency.setName("EscalaFuny");
        agency.setEmail("escalaFuny@gmail.com");
        agency.setPassword("EscalaFuny123");
        agency.setPhoneNumber(999876123);
        agency.setDescription("Somos una agencia de escalada");
        agency.setLocation("Rio seco 24");
        agency.setRuc(123456789);
        agency.setPhoto("picture.jpg");
        agency.setScore(10);

        assertEquals(1L, agency.getId());
        assertEquals("EscalaFuny", agency.getName());
        assertEquals("escalaFuny@gmail.com", agency.getEmail());
        assertEquals("EscalaFuny123", agency.getPassword());
        assertEquals(999876123, agency.getPhoneNumber());
        assertEquals("Somos una agencia de escalada", agency.getDescription());
        assertEquals("Rio seco 24", agency.getLocation());
        assertEquals(123456789, agency.getRuc());
        assertEquals("picture.jpg", agency.getPhoto());
        assertEquals(10, agency.getScore());
    }
}

