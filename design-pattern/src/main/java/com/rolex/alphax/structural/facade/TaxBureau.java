/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.structural.facade;

import lombok.extern.slf4j.Slf4j;

/**
 * @author rolex
 * @since 2020
 */
@Slf4j
public class TaxBureau implements Government{

    @Override
    public void approve(){
        log.info("税务局审批");
    }

}
