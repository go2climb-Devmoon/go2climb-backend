package com.go2climb.go2climbapi.application.agencyReviews.resource;

import com.go2climb.go2climbapi.application.agencies.resource.AgencyResource;
import com.go2climb.go2climbapi.application.tourists.resource.TouristResource;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class AgencyReviewResource {
    private Long id;
    private String date;
    private String comment;
    private float professionalism;
    private float security;
    private float quality;
    private float cost;
    private AgencyResource agency;
    private TouristResource tourist;
}
