package com.go2climb.go2climbapi.application.agencyReviews.domain.persistence;

import com.go2climb.go2climbapi.application.agencyReviews.domain.model.entity.AgencyReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgencyReviewRepository extends JpaRepository<AgencyReview, Long> {
    List<AgencyReview> findByAgencyId(Long agencyId);

    Optional<AgencyReview> findByIdAndAgencyId(Long id, Long agencyId);
    Page<AgencyReview> findByAgencyId(Long agencyId, Pageable pageable);

    List<AgencyReview> findByTouristId(Long touristId);
    Optional<AgencyReview> findByIdAndTouristId(Long id, Long touristId);

    Optional<AgencyReview> findByIdAndAgencyIdAndTouristId(Long id, Long agencyId, Long touristId);
}
