package com.go2climb.go2climbapi.application.agencies.service;


import com.go2climb.go2climbapi.application.agencies.domain.model.entity.Agency;
import com.go2climb.go2climbapi.application.agencies.domain.persistence.AgencyRepository;
import com.go2climb.go2climbapi.shared.exception.ResourceNotFoundException;
import com.go2climb.go2climbapi.shared.exception.ResourceValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AgencyServiceInTest {
    @Mock
    private AgencyRepository agencyRepository;
    @Mock
    private Validator validator;
    AgencyServiceIn agencyServiceIn;
    @BeforeEach
    public void setUp() {
        agencyServiceIn = new AgencyServiceIn(agencyRepository, validator);
    }
    @Test
    public void GetInfoAgencyByIdTest() {
        Agency agencySimulado = mock(Agency.class);
        agencySimulado = new Agency(1L, "EscalaFuny", "escalaFuny@gmail.com",
                "EscalaFuny123", 999876123, "Somos una agencia de escalada",
                "Rio seco 24", 123456789, "picture.jpg", 10);

        Agency esperado = new Agency(1L, "EscalaFuny", "escalaFuny@gmail.com",
                "EscalaFuny123", 999876123, "Somos una agencia de escalada",
                "Rio seco 24", 123456789, "picture.jpg", 10);

        when(agencyRepository.findById(1L)).thenReturn(Optional.of(agencySimulado));

        final Agency actual = agencyServiceIn.getInfoAgencyById(1L);

        Assertions.assertEquals(esperado.getId(), actual.getId());
    }

    @Test
    public void testUpdateAgency() {
        // Datos de prueba
        Long agencyId = 1L;
        Agency request = new Agency(agencyId, "NuevoNombre", "nuevoemail@gmail.com",
                "NuevaClave", 123456789, "NuevaDescripción",
                "NuevaUbicación", 987654321, "nueva_foto.jpg", 5);


        when(validator.validate(request)).thenReturn(Collections.emptySet());

        Agency agencyInDatabase = new Agency(1L, "NombreOriginal", "emailoriginal@gmail.com",
                "ClaveOriginal", 987654321, "DescripciónOriginal",
                "UbicaciónOriginal", 123456789, "foto_original.jpg", 5);

        when(agencyRepository.findById(agencyId)).thenReturn(Optional.of(agencyInDatabase));
        when(agencyRepository.save(any(Agency.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Llamada al método que se va a probar
        Agency updatedAgency = agencyServiceIn.update(agencyId, request);

        Assertions.assertEquals(request.getId(), updatedAgency.getId());
    }

    @Test
    public void testGetAllAgencies() {
        // Datos de prueba
        List<Agency> agencies = Arrays.asList(
                new Agency(1L, "Agencia1", "email1@gmail.com", "clave1", 123456789,
                        "Descripción1", "Ubicación1", 987654321, "foto1.jpg", 5),
                new Agency(2L, "Agencia2", "email2@gmail.com", "clave2", 987654321,
                        "Descripción2", "Ubicación2", 123456789, "foto2.jpg", 4)
        );

        when(agencyRepository.findAll()).thenReturn(agencies);

        List<Agency> returnedAgencies = agencyServiceIn.getAll();

        Assertions.assertEquals(agencies, returnedAgencies);
    }
}