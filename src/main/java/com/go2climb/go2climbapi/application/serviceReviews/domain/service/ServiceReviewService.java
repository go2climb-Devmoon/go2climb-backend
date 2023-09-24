package com.go2climb.go2climbapi.application.serviceReviews.domain.service;

import com.go2climb.go2climbapi.application.serviceReviews.domain.model.entity.ServiceReview;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ServiceReviewService {
    List<ServiceReview> getAll();
    ServiceReview getById(Long serviceReviewId);
    List<ServiceReview> getAllByServiceId(Long serviceId);
    List<ServiceReview> getAllByTouristId(Long touristId);

    ServiceReview create(Long serviceId, Long touristId, ServiceReview serviceReview);
    ServiceReview update(Long serviceId, Long touristId, Long serviceReviewId, ServiceReview serviceReview);
    ResponseEntity<?> delete(Long serviceId, Long touristId, Long serviceReviewId);
}
