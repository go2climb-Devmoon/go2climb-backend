package com.go2climb.go2climbapi.application.agencyReviews.api;

import com.go2climb.go2climbapi.application.agencyReviews.domain.service.AgencyReviewService;
import com.go2climb.go2climbapi.application.agencyReviews.mapping.AgencyReviewMapper;
import com.go2climb.go2climbapi.application.agencyReviews.resource.AgencyReviewResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Tag(name = "Agency reviews", description = "Read agency reviews")
@RestController
@RequestMapping("api/v1/agency-reviews")
public class AgencyReviewController {

    private final AgencyReviewService agencyReviewService;

    private final AgencyReviewMapper mapper;


    public AgencyReviewController(AgencyReviewService agencyReviewService, AgencyReviewMapper mapper) {
        this.agencyReviewService = agencyReviewService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get All agency reviews", description = "Get all agency reviews stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agency reviews found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AgencyReviewResource.class))})
    })
    @GetMapping
    public Page<AgencyReviewResource> getAllAgencyReviews(Pageable pageable) {
        return mapper.modelListPage(agencyReviewService.getAll(), pageable);
    }

    @Operation(summary = "Get Agency reviews by tourist Id", description = "Get all agency reviews by tourist Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agency reviews found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AgencyReviewResource.class))})
    })
    @GetMapping("touristId={touristId}")
    public Page<AgencyReviewResource> getAllByTouristId(@PathVariable Long touristId, Pageable pageable) {
        return mapper.modelListPage(agencyReviewService.getAllByTouristId(touristId), pageable);
    }
}
