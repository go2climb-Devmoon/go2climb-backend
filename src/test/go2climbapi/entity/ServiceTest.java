package com.go2climb.go2climbapi.entity;

import com.go2climb.go2climbapi.application.agencies.domain.model.entity.Agency;
import com.go2climb.go2climbapi.application.services.domain.model.entity.Service;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    @Test
    public void test() {
        Agency request = new Agency(1L, "NuevoNombre", "nuevoemail@gmail.com",
                "NuevaClave", 123456789, "NuevaDescripción",
                "NuevaUbicación", 987654321, "nueva_foto.jpg", 5);

        Service service = new Service();
        service.setId(1L);
        service.setName("Servicio1");
        service.setDescription("Descripción1");
        service.setLocation("Rio seco");
        service.setPrice(50);
        service.setNewPrice(45);
        service.setCreationDate("20/09/23");
        service.setPhotos("foto.jpg");
        service.setIsOffer(0);
        service.setIsPopular(0);
        service.setAgency(request);

        assertEquals(1L, service.getId());
        assertEquals("Servicio1", service.getName());
        assertEquals("Descripción1", service.getDescription());
        assertEquals("Rio seco", service.getLocation());
        assertEquals(50, service.getPrice());
        assertEquals(45, service.getNewPrice());
        assertEquals("20/09/23", service.getCreationDate());
        assertEquals("foto.jpg", service.getPhotos());
        assertEquals(0, service.getIsOffer());
        assertEquals(0, service.getIsPopular());
        assertEquals(request, service.getAgency());
    }

}

