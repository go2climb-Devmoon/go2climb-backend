package com.go2climb.go2climbapi.application.serviceReviews.service;

import com.go2climb.go2climbapi.application.serviceReviews.domain.model.entity.ServiceReview;
import com.go2climb.go2climbapi.application.serviceReviews.domain.persistence.ServiceReviewRepository;
import com.go2climb.go2climbapi.application.serviceReviews.domain.service.ServiceReviewService;
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
public class ServiceReviewServiceImpl implements ServiceReviewService {

    private static final String ENTITY = "ServiceReview";

    private final ServiceReviewRepository serviceReviewRepository;

    private final ServiceRepository serviceRepository;

    private final TouristRepository touristRepository;

    private final Validator validator;

    public ServiceReviewServiceImpl(ServiceReviewRepository serviceReviewRepository, ServiceRepository serviceRepository, TouristRepository touristRepository, Validator validator) {
        this.serviceReviewRepository = serviceReviewRepository;
        this.serviceRepository = serviceRepository;
        this.touristRepository = touristRepository;
        this.validator = validator;
    }

    @Override
    public List<ServiceReview> getAll() {
        return serviceReviewRepository.findAll();
    }

    @Override
    public ServiceReview getById(Long serviceReviewId) {
        return serviceReviewRepository.findById(serviceReviewId).orElseThrow(() ->
                new ResourceNotFoundException(ENTITY, serviceReviewId));
    }

    @Override
    public List<ServiceReview> getAllByServiceId(Long serviceId) {
        return serviceReviewRepository.findByServiceId(serviceId);
    }

    @Override
    public List<ServiceReview> getAllByTouristId(Long touristId) {
        return serviceReviewRepository.findByTouristId(touristId);
    }

    @Override
    public ServiceReview create(Long serviceId, Long touristId, ServiceReview serviceReview) {
        Set<ConstraintViolation<ServiceReview>> violations = validator.validate(serviceReview);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if(!serviceRepository.existsById(serviceId))
            throw new ResourceNotFoundException("Service", serviceId);

        Optional<Service> serviceExisting =  serviceRepository.findById(serviceId);

        return touristRepository.findById(touristId).map(tourist -> {
            serviceReview.setService(serviceExisting.get());
            serviceReview.setTourist(tourist);
            return serviceReviewRepository.save(serviceReview);
        }).orElseThrow(() -> new ResourceNotFoundException("Tourist", touristId));
    }

    @Override
    public ServiceReview update(Long serviceId, Long touristId, Long serviceReviewId, ServiceReview serviceReview) {
        Set<ConstraintViolation<ServiceReview>> violations = validator.validate(serviceReview);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if(!serviceRepository.existsById(serviceId))
            throw new ResourceNotFoundException("Service", serviceId);

        if(!touristRepository.existsById(touristId))
            throw new ResourceNotFoundException("Tourist", touristId);

        return serviceReviewRepository.findById(serviceReviewId).map(existingServiceReview ->
                        serviceReviewRepository.save(existingServiceReview.withComment(serviceReview.getComment())
                                .withScore(serviceReview.getScore()).withDate(serviceReview.getDate())))
                .orElseThrow(() -> new ResourceNotFoundException("ServiceReview", serviceReviewId));
    }

    @Override
    public ResponseEntity<?> delete(Long serviceId, Long touristId, Long serviceReviewId) {
        return serviceReviewRepository.findByIdAndServiceIdAndTouristId(serviceReviewId, serviceId, touristId).map(serviceReview -> {
            serviceReviewRepository.delete(serviceReview);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, serviceReviewId));
    }
}
