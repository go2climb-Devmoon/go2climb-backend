package com.go2climb.go2climbapi.application.services.mapping;

import com.go2climb.go2climbapi.application.services.domain.model.entity.Service;
import com.go2climb.go2climbapi.application.services.resource.CreateServiceResource;
import com.go2climb.go2climbapi.application.services.resource.ServiceResource;
import com.go2climb.go2climbapi.application.services.resource.UpdateServiceResource;
import com.go2climb.go2climbapi.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ServiceMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public ServiceResource toResource(Service model) { return mapper.map(model, ServiceResource.class); }

    public Service toModel(CreateServiceResource resource) { return mapper.map(resource, Service.class); }

    public Service toModel(UpdateServiceResource resource) { return mapper.map(resource, Service.class); }

    public Page<ServiceResource> modelListPage(List<Service> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, ServiceResource.class), pageable, modelList.size());
    }

}
