package com.go2climb.go2climbapi.application.agencyReviews.mapping;

import com.go2climb.go2climbapi.application.agencyReviews.domain.model.entity.AgencyReview;
import com.go2climb.go2climbapi.application.agencyReviews.resource.AgencyReviewResource;
import com.go2climb.go2climbapi.application.agencyReviews.resource.CreateAgencyReviewResource;
import com.go2climb.go2climbapi.application.agencyReviews.resource.UpdateAgencyReviewResource;
import com.go2climb.go2climbapi.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class AgencyReviewMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public AgencyReviewResource toResource(AgencyReview model) { return mapper.map(model, AgencyReviewResource.class); }

    public AgencyReview toModel(CreateAgencyReviewResource resource) { return mapper.map(resource, AgencyReview.class); }

    public AgencyReview toModel(UpdateAgencyReviewResource resource) { return mapper.map(resource, AgencyReview.class); }

    public Page<AgencyReviewResource> modelListPage(List<AgencyReview> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, AgencyReviewResource.class), pageable, modelList.size());
    }
}
