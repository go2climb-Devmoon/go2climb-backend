package com.go2climb.go2climbapi.application.services.resource;

import com.go2climb.go2climbapi.application.agencies.resource.AgencyResource;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResource {
    private Long id;
    private String name;
    private String description;
    private String location;
    private int score;
    private float price;
    private float newPrice;
    private String creationDate;
    private String photos;
    private int isOffer;
    private int isPopular;
    private AgencyResource agency;
}
