/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author rolex
 * @since 2020
 */
@Component
@Slf4j
public class ApplicationServiceFallback implements ApplicationService {
    @Override
    public String service2() {
        log.error("service2 fallback");
        return "service2(none)";
    }
}
