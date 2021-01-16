package com.rolex.alphax.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author rolex
 * @since 2020
 */
@FeignClient(value = "application-service-2", fallback = ApplicationServiceFallback.class)
@Component
public interface ApplicationService {

    @GetMapping("/service2")
    String service2();

}
