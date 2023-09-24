package com.go2climb.go2climbapi.application.tourists.api;

import com.go2climb.go2climbapi.application.tourists.domain.service.TouristService;
import com.go2climb.go2climbapi.application.tourists.mapping.TouristMapper;
import com.go2climb.go2climbapi.application.tourists.resource.CreateTouristResource;
import com.go2climb.go2climbapi.application.tourists.resource.TouristResource;
import com.go2climb.go2climbapi.application.tourists.resource.UpdateTouristResource;
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
@Tag(name = "Tourists", description = "Create, read, update and delete tourists")
@RestController
@RequestMapping("api/v1/tourists")
public class TouristController {
    private final TouristService touristService;
    private final TouristMapper mapper;

    public TouristController(TouristService touristService, TouristMapper mapper) {
        this.touristService = touristService;
        this.mapper = mapper;
    }

    //funciona
    @Operation(summary = "Get All Tourists", description = "Get all tourists stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tourists found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TouristResource.class))})
    })
    @GetMapping
    public Page<TouristResource> getAllTourists(Pageable pageable)
    {
        return mapper.modelListPage(touristService.getAll(), pageable);
    }

    //funciona GET BY ID
    @Operation(summary = "Get Tourist by Id", description = "Get an tourist by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tourist found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TouristResource.class))})
    })
    @GetMapping("{touristId}")
    public TouristResource getInfoUserById(@PathVariable Long touristId) {
        return mapper.toResource(touristService.getInfoUserById(touristId));
    }

    // funciona GET BY NAME
    @Operation(summary = "Get Tourist by Name", description = "Get an tourist by Name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tourist found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TouristResource.class))})
    })
    @GetMapping("name/{touristName}")
    public TouristResource getInfoUserByName(@PathVariable("touristName") String touristName) {
        return mapper.toResource(touristService.getInfoUserByName(touristName));
    }

    // funciona GET BY EMAIL AND PASSWORD
    @Operation(summary = "Get Tourist by email and password", description = "Get an tourist by email and password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tourist found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TouristResource.class))})
    })
    @GetMapping("email&password/{touristEmail}/{touristPassword}")
    public TouristResource getInfoUserByEmailAndPassword(@PathVariable("touristEmail") String touristEmail, @PathVariable("touristPassword") String touristPassword) {
        return mapper.toResource(touristService.getInfoUserByEmailAndPassword(touristEmail,touristPassword));
    }






    //funciona
    @Operation(summary = "Create Tourist", description = "Create tourist in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tourist created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TouristResource.class)
                    ))
    })
    @PostMapping
    public TouristResource createUser(@RequestBody CreateTouristResource resource){
        return mapper.toResource(touristService.create(mapper.toModel(resource)));
    }

    //funciona
    @Operation(summary = "Update an Tourist information", description = "Update an tourist information in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tourist updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TouristResource.class)
                    ))
    })
    @PutMapping("/{touristId}")
    public TouristResource updateUser(@PathVariable Long touristId, @RequestBody UpdateTouristResource resource) {
        return mapper.toResource(touristService.update(touristId, mapper.toModel(resource)));
    }

    //funciona
    @Operation(summary = "Delete an Tourist", description = "Delete an tourist from database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tourist deleted", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("{touristId}")
    public ResponseEntity<?> deleteTourist(@PathVariable Long touristId) {
        return touristService.delete(touristId);
    }
}
