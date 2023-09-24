package com.go2climb.go2climbapi.mapping;

import com.go2climb.go2climbapi.application.agencies.mapping.AgencyMapper;
import com.go2climb.go2climbapi.application.agencyReviews.mapping.AgencyReviewMapper;
import com.go2climb.go2climbapi.application.hiredServices.mapping.HiredServiceMapper;
import com.go2climb.go2climbapi.application.serviceReviews.mapping.ServiceReviewMapper;
import com.go2climb.go2climbapi.application.services.mapping.ServiceMapper;
import com.go2climb.go2climbapi.application.tourists.mapping.TouristMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("Go2ClimbAPIMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public AgencyMapper AgencyMapper() { return new AgencyMapper(); }
    @Bean
    public HiredServiceMapper hiredServiceMapper() { return new HiredServiceMapper(); }
    @Bean
    public TouristMapper touristMapper() { return new TouristMapper(); }
    @Bean
    public ServiceMapper serviceMapper() { return new ServiceMapper(); }
    @Bean
    public AgencyReviewMapper agencyReviewMapper() { return new AgencyReviewMapper(); }
    @Bean
    public ServiceReviewMapper serviceReviewMapper() { return new ServiceReviewMapper(); }
}