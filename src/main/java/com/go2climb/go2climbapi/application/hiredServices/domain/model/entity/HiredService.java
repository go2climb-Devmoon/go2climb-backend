package com.go2climb.go2climbapi.application.hiredServices.domain.model.entity;

import com.go2climb.go2climbapi.application.tourists.domain.model.entity.Tourist;
import com.go2climb.go2climbapi.application.services.domain.model.entity.Service;
import com.go2climb.go2climbapi.shared.domain.model.AuditModel;
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
@Table(name = "hired_services")
public class HiredService extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int amount;

    @NotNull
    private Double price;

    @NotNull
    @NotBlank
    private String scheduledDate;

    @NotNull
    @NotBlank
    private String status;

    //Relationships
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tourist_id", nullable = false)
    private Tourist tourist;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;
}
