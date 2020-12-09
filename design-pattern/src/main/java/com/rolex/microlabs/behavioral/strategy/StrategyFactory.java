/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.behavioral.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rolex
 * @since 2020
 */
public class StrategyFactory {

    private static final Map<String, RateStrategy> strategies = new HashMap();

    static {
        strategies.put("blackcard", new BlackCardStrategy());
        strategies.put("goldcard", new GoldCardStrategy());
        strategies.put("silvercard", new SilverCardStrategy());
    }

    public RateStrategy getStrategy(String type){
        return strategies.get(type);
    }

}
