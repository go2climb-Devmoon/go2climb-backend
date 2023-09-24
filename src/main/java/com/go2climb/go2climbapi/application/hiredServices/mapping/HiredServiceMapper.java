package com.go2climb.go2climbapi.application.hiredServices.mapping;

import com.go2climb.go2climbapi.application.hiredServices.domain.model.entity.HiredService;
import com.go2climb.go2climbapi.application.hiredServices.resource.CreateHiredServiceResource;
import com.go2climb.go2climbapi.application.hiredServices.resource.HiredServiceResource;
import com.go2climb.go2climbapi.application.hiredServices.resource.PatchHiredServiceResource;
import com.go2climb.go2climbapi.application.hiredServices.resource.UpdateHiredServiceResource;
import com.go2climb.go2climbapi.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class HiredServiceMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public HiredServiceResource toResource(HiredService model) { return mapper.map(model, HiredServiceResource.class); }

    public HiredService toModel(CreateHiredServiceResource resource) { return mapper.map(resource, HiredService.class); }

    public HiredService toModel(UpdateHiredServiceResource resource) { return mapper.map(resource, HiredService.class); }

    public HiredService toModel(PatchHiredServiceResource resource) { return mapper.map(resource, HiredService.class); }

    public Page<HiredServiceResource> modelListPage (List<HiredService> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, HiredServiceResource.class), pageable, modelList.size());
    }

}
