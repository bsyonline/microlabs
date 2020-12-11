/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.behavioral.chainofresponsibility;

import lombok.extern.slf4j.Slf4j;

/**
 * @author rolex
 * @since 2020
 */
@Slf4j
public class HttpHeaderHandler extends Handler{

    @Override
    public void handle() {
        log.info("wrapper http header");
    }
}
