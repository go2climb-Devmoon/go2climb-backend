package com.go2climb.go2climbapi.application.agencies.mapping;

import com.go2climb.go2climbapi.application.agencies.resource.AgencyResource;
import com.go2climb.go2climbapi.application.agencies.resource.CreateAgencyResource;
import com.go2climb.go2climbapi.shared.mapping.EnhancedModelMapper;
import com.go2climb.go2climbapi.application.agencies.domain.model.entity.Agency;
import com.go2climb.go2climbapi.application.agencies.resource.UpdateAgencyResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class AgencyMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public AgencyResource toResource(Agency model) { return mapper.map(model, AgencyResource.class); }

    public Agency toModel(CreateAgencyResource resource) { return mapper.map(resource, Agency.class); }

    public Agency toModel(UpdateAgencyResource resource) { return mapper.map(resource, Agency.class); }

    public Page<AgencyResource> modelListPage(List<Agency> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, AgencyResource.class), pageable, modelList.size());
    }
}
