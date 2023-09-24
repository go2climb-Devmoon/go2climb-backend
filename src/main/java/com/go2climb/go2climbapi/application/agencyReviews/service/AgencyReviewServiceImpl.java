package com.go2climb.go2climbapi.application.agencyReviews.service;

import com.go2climb.go2climbapi.application.agencies.domain.persistence.AgencyRepository;
import com.go2climb.go2climbapi.application.agencyReviews.domain.model.entity.AgencyReview;
import com.go2climb.go2climbapi.application.agencyReviews.domain.persistence.AgencyReviewRepository;
import com.go2climb.go2climbapi.application.agencyReviews.domain.service.AgencyReviewService;
import com.go2climb.go2climbapi.application.agencies.domain.model.entity.Agency;
import com.go2climb.go2climbapi.application.tourists.domain.persistence.TouristRepository;
import com.go2climb.go2climbapi.shared.exception.ResourceNotFoundException;
import com.go2climb.go2climbapi.shared.exception.ResourceValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AgencyReviewServiceImpl implements AgencyReviewService {

    private static final String ENTITY = "AgencyReview";

    private final AgencyReviewRepository agencyReviewRepository;

    private final AgencyRepository agencyRepository;

    private final TouristRepository touristRepository;

    private final Validator validator;

    public AgencyReviewServiceImpl(AgencyReviewRepository agencyReviewRepository, AgencyRepository agencyRepository, TouristRepository touristRepository, Validator validator) {
        this.agencyReviewRepository = agencyReviewRepository;
        this.agencyRepository = agencyRepository;
        this.touristRepository = touristRepository;
        this.validator = validator;
    }

    @Override
    public List<AgencyReview> getAll() {
        return agencyReviewRepository.findAll();
    }

    @Override
    public AgencyReview getById(Long agencyReviewId) {
        return agencyReviewRepository.findById(agencyReviewId).orElseThrow(() ->
                new ResourceNotFoundException(ENTITY, agencyReviewId));
    }

    @Override
    public List<AgencyReview> getAllByAgencyId(Long agencyId) {
        return agencyReviewRepository.findByAgencyId(agencyId);
    }

    @Override
    public List<AgencyReview> getAllByTouristId(Long touristId) {
        return agencyReviewRepository.findByTouristId(touristId);
    }

    @Override
    public AgencyReview create(Long agencyId, Long touristId, AgencyReview agencyReview) {
        Set<ConstraintViolation<AgencyReview>> violations = validator.validate(agencyReview);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if(!agencyRepository.existsById(agencyId))
            throw new ResourceNotFoundException("Agency", agencyId);

        Optional<Agency> agencyExisting =  agencyRepository.findById(agencyId);

        return touristRepository.findById(touristId).map(tourist -> {
            agencyReview.setAgency(agencyExisting.get());
            agencyReview.setTourist(tourist);
            return agencyReviewRepository.save(agencyReview);
        }).orElseThrow(() -> new ResourceNotFoundException("Tourist", touristId));
    }

    @Override
    public AgencyReview update(Long agencyId, Long touristId, Long agencyReviewId, AgencyReview agencyReview) {
        Set<ConstraintViolation<AgencyReview>> violations = validator.validate(agencyReview);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if(!agencyRepository.existsById(agencyId))
            throw new ResourceNotFoundException("Agency", agencyId);

        if(!touristRepository.existsById(touristId))
            throw new ResourceNotFoundException("Tourist", touristId);

        return agencyReviewRepository.findById(agencyReviewId).map(existingAgencyReview ->
                agencyReviewRepository.save(existingAgencyReview.withComment(agencyReview.getComment())
                        .withProfessionalism(agencyReview.getProfessionalism())
                        .withSecurity(agencyReview.getSecurity())
                        .withQuality(agencyReview.getQuality())
                        .withCost(agencyReview.getCost())
                        .withDate(agencyReview.getDate())))
                .orElseThrow(() -> new ResourceNotFoundException("AgencyReview", agencyReviewId));
    }

    @Override
    public ResponseEntity<?> delete(Long agencyId, Long touristId, Long agencyReviewId) {
        return agencyReviewRepository.findByIdAndAgencyIdAndTouristId(agencyReviewId, agencyId, touristId).map(agencyReview -> {
            agencyReviewRepository.delete(agencyReview);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, agencyReviewId));
    }
}
