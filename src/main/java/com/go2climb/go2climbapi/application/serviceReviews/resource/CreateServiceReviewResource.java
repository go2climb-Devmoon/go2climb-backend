package com.go2climb.go2climbapi.application.serviceReviews.resource;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateServiceReviewResource {
    @NotNull
    @NotBlank
    private String comment;

    @NotNull
    @NotBlank
    private String date;

    @NotNull
    private float score;
}
