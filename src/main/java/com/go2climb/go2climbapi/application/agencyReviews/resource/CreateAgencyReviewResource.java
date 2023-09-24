package com.go2climb.go2climbapi.application.agencyReviews.resource;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateAgencyReviewResource {
    @NotNull
    @NotBlank
    private String comment;

    @NotNull
    @NotBlank
    private String date;

    @NotNull
    private float professionalism;

    @NotNull
    private float security;

    @NotNull
    private float quality;

    @NotNull
    private float cost;
}
