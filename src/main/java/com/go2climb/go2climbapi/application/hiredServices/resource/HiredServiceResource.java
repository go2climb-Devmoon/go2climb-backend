package com.go2climb.go2climbapi.application.hiredServices.resource;

import com.go2climb.go2climbapi.application.tourists.resource.TouristResource;
import com.go2climb.go2climbapi.application.services.resource.ServiceResource;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class HiredServiceResource {
    private Long id;
    private int amount;
    private Double price;
    private String scheduledDate;
    private String status;

    //Relationships
    private TouristResource tourist;
    private ServiceResource service;
}
