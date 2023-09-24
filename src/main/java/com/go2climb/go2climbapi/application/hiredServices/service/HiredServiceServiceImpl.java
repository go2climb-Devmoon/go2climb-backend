package com.go2climb.go2climbapi.application.hiredServices.service;

import com.go2climb.go2climbapi.application.hiredServices.domain.model.entity.HiredService;
import com.go2climb.go2climbapi.application.hiredServices.domain.persistence.HiredServiceRepository;
import com.go2climb.go2climbapi.application.hiredServices.domain.service.HiredServiceService;
import com.go2climb.go2climbapi.application.tourists.domain.persistence.TouristRepository;
import com.go2climb.go2climbapi.application.services.domain.model.entity.Service;
import com.go2climb.go2climbapi.application.services.domain.persistence.ServiceRepository;
import com.go2climb.go2climbapi.shared.exception.ResourceNotFoundException;
import com.go2climb.go2climbapi.shared.exception.ResourceValidationException;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@org.springframework.stereotype.Service
public class HiredServiceServiceImpl implements HiredServiceService {
    private static final String ENTITY = "HiredService";

    private final HiredServiceRepository hiredServiceRepository;

    private final ServiceRepository serviceRepository;

    private final TouristRepository touristRepository;

    private final Validator validator;

    public HiredServiceServiceImpl(HiredServiceRepository hiredServiceRepository, ServiceRepository serviceRepository, TouristRepository touristRepository, Validator validator) {
        this.hiredServiceRepository = hiredServiceRepository;
        this.serviceRepository = serviceRepository;
        this.touristRepository = touristRepository;
        this.validator = validator;
    }

    @Override
    public List<HiredService> getAll() {
        return hiredServiceRepository.findAll();
    }

    @Override
    public HiredService getById(Long hiredServiceId) {
        return hiredServiceRepository.findById(hiredServiceId).orElseThrow(() ->
                new ResourceNotFoundException(ENTITY, hiredServiceId));
    }

    @Override
    public List<HiredService> getAllByServiceId(Long serviceId) {
        return hiredServiceRepository.findByServiceId(serviceId);
    }

    @Override
    public List<HiredService> getAllByTouristId(Long touristId) {
        return hiredServiceRepository.findByTouristId(touristId);
    }

    @Override
    public HiredService create(Long serviceId, Long touristId, HiredService hiredService) {
        Set<ConstraintViolation<HiredService>> violations = validator.validate(hiredService);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if(!serviceRepository.existsById(serviceId))
            throw new ResourceNotFoundException("Service", serviceId);

        Optional<Service> serviceExisting =  serviceRepository.findById(serviceId);

        return touristRepository.findById(touristId).map(tourist -> {
            hiredService.setService(serviceExisting.get());
            hiredService.setTourist(tourist);
            return hiredServiceRepository.save(hiredService);
        }).orElseThrow(() -> new ResourceNotFoundException("Tourist", touristId));
    }

    @Override
    public HiredService update(Long serviceId, Long touristId, Long hiredServiceId, HiredService hiredService) {
        Set<ConstraintViolation<HiredService>> violations = validator.validate(hiredService);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if(!serviceRepository.existsById(serviceId))
            throw new ResourceNotFoundException("Service", serviceId);

        if(!touristRepository.existsById(touristId))
            throw new ResourceNotFoundException("Tourist", touristId);

        return hiredServiceRepository.findById(hiredServiceId).map(existingHiredService ->
                        hiredServiceRepository.save(existingHiredService.withAmount(hiredService.getAmount())
                                .withPrice(hiredService.getPrice())
                                .withScheduledDate(hiredService.getScheduledDate())
                                .withStatus(hiredService.getStatus())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, hiredServiceId));
    }

    /*
    @Override
    public HiredService patch(Long serviceId, Long touristId, Long hiredServiceId, HiredService hiredService) {
        Set<ConstraintViolation<HiredService>> violations = validator.validate(hiredService);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if(!serviceRepository.existsById(serviceId))
            throw new ResourceNotFoundException("Service", serviceId);

        if(!touristRepository.existsById(touristId))
            throw new ResourceNotFoundException("Tourist", touristId);

        return hiredServiceRepository.findById(hiredServiceId).map(existingHiredService ->
                        hiredServiceRepository.save(existingHiredService.withAmount(hiredService.getAmount())
                                .withPrice(hiredService.getPrice())
                                .withScheduledDate(hiredService.getScheduledDate())
                                .withStatus(hiredService.getStatus())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, hiredServiceId));
    }*/

    @Override
    public ResponseEntity<?> delete(Long serviceId, Long touristId, Long hiredServiceId) {
        return hiredServiceRepository.findByIdAndServiceIdAndTouristId(hiredServiceId, serviceId, touristId).map(hiredService -> {
            hiredServiceRepository.delete(hiredService);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, hiredServiceId));
    }
}
