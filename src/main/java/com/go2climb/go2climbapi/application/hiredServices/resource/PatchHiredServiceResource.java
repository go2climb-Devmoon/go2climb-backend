package com.go2climb.go2climbapi.application.hiredServices.resource;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class PatchHiredServiceResource {
    private int amount;
    private Double price;
    private String scheduledDate;
    private String status;
}
