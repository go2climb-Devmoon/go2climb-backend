package com.go2climb.go2climbapi.application.serviceReviews.resource;

import com.go2climb.go2climbapi.application.tourists.resource.TouristResource;
import com.go2climb.go2climbapi.application.services.resource.ServiceResource;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ServiceReviewResource {
    private Long id;
    private String date;
    private String comment;
    private float score;
    private ServiceResource service;
    private TouristResource tourist;
}
