package com.go2climb.go2climbapi.application.services.service;

import com.go2climb.go2climbapi.application.agencies.domain.model.entity.Agency;
import com.go2climb.go2climbapi.application.agencies.domain.persistence.AgencyRepository;
import com.go2climb.go2climbapi.application.agencies.service.AgencyServiceIn;
import com.go2climb.go2climbapi.application.services.domain.model.entity.Service;
import com.go2climb.go2climbapi.application.services.domain.persistence.ServiceRepository;
import com.go2climb.go2climbapi.shared.exception.ResourceNotFoundException;
import com.go2climb.go2climbapi.shared.exception.ResourceValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.util.*;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceServiceImplTest {
    @Mock
    private ServiceRepository serviceRepository;
    @Mock
    private AgencyRepository agencyRepository;
    @Mock
    private Validator validator;
    @Captor
    private ArgumentCaptor<Service> serviceCaptor;
    ServiceServiceImpl serviceServiceImpl;
    @BeforeEach
    public void setUp() {
        serviceServiceImpl = new ServiceServiceImpl(serviceRepository, agencyRepository, validator);
    }
    @Test
    public void testGetAllServicesByAgencyId() {
        // ID de agencia de prueba
        Long agencyId = 1L;
        Agency request = new Agency(agencyId, "NuevoNombre", "nuevoemail@gmail.com",
                "NuevaClave", 123456789, "NuevaDescripción",
                "NuevaUbicación", 987654321, "nueva_foto.jpg", 5);
        // Datos de prueba: Lista de servicios para la agencia con el ID de agencia de prueba
        List<Service> services = Arrays.asList(
                new Service(1L, "Servicio1", "Descripción1", "Escalada", 50,45,
                        75,"12/04/23", "foto.jpg", 0, 0, request ),
                new Service(2L, "Servicio2", "Descripción2", "Alpinismo", 75,70,
                        100,"12/04/23", "foto.jpg", 0, 0,request)
        );
        when(serviceRepository.findByAgencyId(agencyId)).thenReturn(services);
        List<Service> returnedServices = serviceServiceImpl.getAllByAgencyId(agencyId);
        assertEquals(services, returnedServices);
    }

    @Test
    void testCreateValidService() {
        // Mock de agencia encontrada por ID
        Long agencyId = 1L;
        Agency agency = new Agency(agencyId, "NombreAgencia", "email@agencia.com", "ClaveAgencia",
                123456789, "DescripciónAgencia", "UbicaciónAgencia", 987654321,
                "foto_agencia.jpg", 5);

        // Servicio de prueba
        Service service = new Service(1L, "Servicio1", "Descripción1", "Escalada", 50,
                45, 75, "12/04/23", "foto.jpg", 0, 0, agency);

        when(agencyRepository.findById(agencyId)).thenReturn(Optional.of(agency));

        // Configuración del mock de validator usando doReturn().when()
        doReturn(Collections.emptySet()).when(validator).validate(service);

        when(serviceRepository.save(service)).thenReturn(service);

        Service createdService = serviceServiceImpl.create(agencyId, service);

        assertEquals(service, createdService);
    }

    @Test
    void testUpdateValidService() {
        // Mock de agencia encontrada por ID
        Long agencyId = 1L;
        Agency agency = new Agency(agencyId, "NombreAgencia", "email@agencia.com", "ClaveAgencia", 123456789, "DescripciónAgencia", "UbicaciónAgencia", 987654321, "foto_agencia.jpg", 5);

        // Datos de prueba
        Long serviceId = 1L;

        // Configuración del mock del repositorio de agencias para que exista la agencia
        when(agencyRepository.existsById(agencyId)).thenReturn(true);

        // Mock de un servicio existente en la base de datos
        Service existingService = new Service(serviceId, "ExistingService", "Description", "Location", 50, 45, 75, "12/04/23", "foto.jpg", 0, 0, agency);

        // Configuración del mock del repositorio de servicios para que devuelva el servicio existente al buscar por ID
        when(serviceRepository.findById(serviceId)).thenReturn(Optional.of(existingService)); // Asegúrate de que este mock devuelva el servicio con ID 1

        // Servicio de prueba con datos actualizados
        Service updatedService = new Service(serviceId, "UpdatedService", "UpdatedDescription", "UpdatedLocation", 60, 55, 80, "13/04/23", "updated.jpg", 1, 1, agency);

        // Configuración del mock de validator para que devuelva un conjunto vacío de violaciones
        doReturn(Collections.emptySet()).when(validator).validate(updatedService);

        // Llamada al método que se va a probar
        Service updatedResult = serviceServiceImpl.update(agencyId, serviceId, updatedService); // Corrección aquí

        // Asegurarse de que el servicio actualizado sea igual al servicio de prueba
        assertEquals(updatedService, updatedResult);
    }

    @Test
    void testDeleteExistingService() {
        // ID de agencia y servicio de prueba
        Long agencyId = 1L;
        Agency agency = new Agency(agencyId, "NombreAgencia", "email@agencia.com", "ClaveAgencia",
                123456789, "DescripciónAgencia", "UbicaciónAgencia", 987654321,
                "foto_agencia.jpg", 5);
        Long serviceId = 2L;

        // Mock de servicio existente
        Service existingService = new Service(serviceId, "ExistingService", "Description",
                "Location", 50, 45, 75, "12/04/23", "foto.jpg",
                0, 0, agency);

        when(serviceRepository.findByIdAndAgencyId(serviceId, agencyId)).thenReturn(Optional.of(existingService));

        ResponseEntity<?> response = serviceServiceImpl.delete(agencyId, serviceId);

        assertEquals(ResponseEntity.ok().build(), response);
    }
}