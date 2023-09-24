package com.go2climb.go2climbapi.application.agencyReviews.api;

import com.go2climb.go2climbapi.application.agencyReviews.domain.service.AgencyReviewService;
import com.go2climb.go2climbapi.application.agencyReviews.mapping.AgencyReviewMapper;
import com.go2climb.go2climbapi.application.agencyReviews.resource.AgencyReviewResource;
import com.go2climb.go2climbapi.application.agencyReviews.resource.CreateAgencyReviewResource;
import com.go2climb.go2climbapi.application.agencyReviews.resource.UpdateAgencyReviewResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Tag(name = "Agency / Agency reviews", description = "Read, create, update and delete agency reviews by agency Id")
@RestController
@RequestMapping("api/v1/agencies/{agencyId}/agency-reviews")
public class AgencyAgencyReviewController {

    private final AgencyReviewService agencyReviewService;

    private final AgencyReviewMapper mapper;

    public AgencyAgencyReviewController(AgencyReviewService agencyReviewService, AgencyReviewMapper mapper) {
        this.agencyReviewService = agencyReviewService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get All agency reviews", description = "Get all agency reviews by agencyId stored in database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agency reviews found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AgencyReviewResource.class))})
    })
    @GetMapping
    public Page<AgencyReviewResource> getAllByAgencyId(@PathVariable Long agencyId, Pageable pageable) {
        return mapper.modelListPage(agencyReviewService.getAllByAgencyId(agencyId), pageable);
    }

    @Operation(summary = "Create an agency review", description = "Create an agency review by agencyId and touristId in database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agency review created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AgencyReviewResource.class))})
    })
    @PostMapping("touristId={touristId}")
    public AgencyReviewResource createAgencyReview(@PathVariable Long agencyId, @PathVariable Long touristId,
                                                   @RequestBody CreateAgencyReviewResource resource) {
        return mapper.toResource(agencyReviewService.create(agencyId, touristId, mapper.toModel(resource)));
    }

    @Operation(summary = "Update an agency review", description = "Update an agency review in database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agency review updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AgencyReviewResource.class))})
    })
    @PutMapping("touristId={touristId}/{agencyReviewId}")
    public AgencyReviewResource updateAgencyReview(@PathVariable Long agencyReviewId, @PathVariable Long touristId,
                                                   @PathVariable Long agencyId, @RequestBody UpdateAgencyReviewResource resource) {
        return mapper.toResource(agencyReviewService.update(agencyId, touristId, agencyReviewId, mapper.toModel(resource)));
    }

    @Operation(summary = "Delete an agency review", description = "Delete an agency review from database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agency review deleted", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("touristId={touristId}/{agencyReviewId}")
    public ResponseEntity<?> deleteAgencyReview(@PathVariable Long agencyReviewId, @PathVariable Long touristId,
                                                @PathVariable Long agencyId) {
        return agencyReviewService.delete(agencyId, touristId, agencyReviewId);
    }

}
