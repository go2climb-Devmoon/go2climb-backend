package com.go2climb.go2climbapi.application.agencies.api;

import com.go2climb.go2climbapi.application.agencies.domain.service.AgencyService;
import com.go2climb.go2climbapi.application.agencies.mapping.AgencyMapper;
import com.go2climb.go2climbapi.application.agencies.resource.AgencyResource;
import com.go2climb.go2climbapi.application.agencies.resource.CreateAgencyResource;
import com.go2climb.go2climbapi.application.agencies.resource.UpdateAgencyResource;
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
@Tag(name = "Agencies", description = "Create, read, update and delete agencies")
@RestController
@RequestMapping(value = "api/v1/agencies")
public class AgencyController {
    private final AgencyService agencyService;
    private final AgencyMapper mapper;

    public AgencyController(AgencyService agencyService, AgencyMapper mapper) {
        this.agencyService = agencyService;
        this.mapper = mapper;
    }

    //funciona GET ALL
    @Operation(summary = "Get All Agencies", description = "Get all agencies stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agencies found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AgencyResource.class))})
    })
    @GetMapping
    public Page<AgencyResource> getAllAgencies(Pageable pageable)
    {
        return mapper.modelListPage(agencyService.getAll(), pageable);
    }

    //funciona GET BY ID
    @Operation(summary = "Get Agency by Id", description = "Get an agency by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agency found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AgencyResource.class))})
    })
    @GetMapping("{agencyId}")
    public AgencyResource getInfoAgencyById(@PathVariable Long agencyId) {
        return mapper.toResource(agencyService.getInfoAgencyById(agencyId));
    }
    //funciona GET BY NAME
    @Operation(summary = "Get Agency by Name", description = "Get an agency by Name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Name found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AgencyResource.class))})
    })
    @GetMapping("name/{agencyName}")
    public AgencyResource getInfoAgencyByName(@PathVariable("agencyName") String agencyName) {
        return mapper.toResource(agencyService.getByName(agencyName));
    }

    // funciona GET BY EMAIL
    @Operation(summary = "Get Agency by Email", description = "Get an agency by Email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AgencyResource.class))})
    })
    @GetMapping("email/{agencyEmail}")
    public AgencyResource getInfoAgencyByEmail(@PathVariable("agencyEmail") String agencyEmail) {
        return mapper.toResource(agencyService.getByEmail(agencyEmail));
    }

    // funciona GET BY PASSWORD AND EMAIL
    @Operation(summary = "Get Agency by email and password", description = "Get an agency by email and password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tourist found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AgencyResource.class))})
    })
    @GetMapping("email&password/{agencyEmail}/{agencyPassword}")
    public AgencyResource getInfoAgencyByEmailAndPassword(@PathVariable("agencyEmail") String agencyEmail, @PathVariable("agencyPassword") String agencyPassword) {
        return mapper.toResource(agencyService.getByEmailAndPassword(agencyEmail,agencyPassword));
    }

    // funciona GET BY LOCATION
    @Operation(summary = "Get Agency by Location", description = "Get an agency by Location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AgencyResource.class))})
    })
    @GetMapping("location/{agencyLocation}")
    public AgencyResource getInfoAgencyByLocation(@PathVariable("agencyLocation") String agencyLocation) {
        return mapper.toResource(agencyService.getByLocation(agencyLocation));
    }




    //funciona POST
    @Operation(summary = "Create Agency", description = "Create Agency in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agency created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AgencyResource.class)
                    ))
    })
    @PostMapping
    public AgencyResource createAgency(@RequestBody CreateAgencyResource resource){
        return mapper.toResource(agencyService.create(mapper.toModel(resource)));
    }

    //funciona UPDATE
    @Operation(summary = "Update an Agency", description = "Update an Agency in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agency updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AgencyResource.class)
                    ))
    })
    @PutMapping("/{agencyId}")
    public AgencyResource updateAgency(@PathVariable Long agencyId, @RequestBody UpdateAgencyResource resource) {
        return mapper.toResource(agencyService.update(agencyId, mapper.toModel(resource)));
    }

    //funciona DELETE
    @Operation(summary = "Delete an Agency", description = "Delete an Agency from database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agency deleted", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("{agencyId}")
    public ResponseEntity<?> deleteAgency(@PathVariable Long agencyId) {
        return agencyService.delete(agencyId);
    }
}
