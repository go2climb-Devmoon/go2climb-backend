package com.go2climb.go2climbapi.application.services.domain.model.entity;

import com.go2climb.go2climbapi.application.agencies.domain.model.entity.Agency;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @NotBlank
    private String location;

    @NotNull
    private int score;

    @NotNull
    private float price;

    @NotNull
    private float newPrice;

    @NotNull
    @NotBlank
    private String creationDate;

    @NotNull
    @NotBlank
    private String photos;

    @NotNull
    private int isOffer;

    @NotNull
    private int isPopular;

    //Relationships
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "agency_id", nullable = false)
    private Agency agency;

}
