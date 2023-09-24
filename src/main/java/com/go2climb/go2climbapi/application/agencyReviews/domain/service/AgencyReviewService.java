package com.go2climb.go2climbapi.application.agencyReviews.domain.service;

import com.go2climb.go2climbapi.application.agencyReviews.domain.model.entity.AgencyReview;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AgencyReviewService {
    List<AgencyReview> getAll();
    AgencyReview getById(Long agencyReviewId);
    List<AgencyReview> getAllByAgencyId(Long agencyId);
    List<AgencyReview> getAllByTouristId(Long touristId);

    AgencyReview create(Long agencyId, Long touristId, AgencyReview agencyReview);
    AgencyReview update(Long agencyId, Long touristId, Long agencyReviewId, AgencyReview agencyReview);
    ResponseEntity<?> delete(Long agencyId, Long touristId, Long agencyReviewId);
}
