package com.go2climb.go2climbapi.application.serviceReviews.mapping;

import com.go2climb.go2climbapi.application.serviceReviews.domain.model.entity.ServiceReview;
import com.go2climb.go2climbapi.application.serviceReviews.resource.CreateServiceReviewResource;
import com.go2climb.go2climbapi.application.serviceReviews.resource.ServiceReviewResource;
import com.go2climb.go2climbapi.application.serviceReviews.resource.UpdateServiceReviewResource;
import com.go2climb.go2climbapi.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ServiceReviewMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public ServiceReviewResource toResource(ServiceReview model) { return mapper.map(model, ServiceReviewResource.class); }

    public ServiceReview toModel(CreateServiceReviewResource resource) { return mapper.map(resource, ServiceReview.class); }

    public ServiceReview toModel(UpdateServiceReviewResource resource) { return mapper.map(resource, ServiceReview.class); }

    public Page<ServiceReviewResource> modelListPage(List<ServiceReview> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, ServiceReviewResource.class), pageable, modelList.size());
    }
}
