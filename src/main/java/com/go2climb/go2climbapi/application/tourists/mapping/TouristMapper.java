package com.go2climb.go2climbapi.application.tourists.mapping;

import com.go2climb.go2climbapi.application.tourists.resource.CreateTouristResource;
import com.go2climb.go2climbapi.application.tourists.domain.model.entity.Tourist;
import com.go2climb.go2climbapi.application.tourists.resource.TouristResource;
import com.go2climb.go2climbapi.application.tourists.resource.UpdateTouristResource;
import com.go2climb.go2climbapi.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class TouristMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;


    public TouristResource toResource(Tourist model) { return mapper.map(model, TouristResource.class); }

    public Tourist toModel(CreateTouristResource resource) { return mapper.map(resource, Tourist.class); }

    public Tourist toModel(UpdateTouristResource resource) { return mapper.map(resource, Tourist.class); }

    public Page<TouristResource> modelListPage(List<Tourist> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, TouristResource.class), pageable, modelList.size());
    }
}
