/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.structural.adapter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rolex
 * @since 2020
 */
public class OuterUserInfo implements IOuterUserInfo {

    @Override
    public Map basicInfo() {
        Map basicInfo = new HashMap();
        basicInfo.put("name", "alice");
        basicInfo.put("age", "22");
        return basicInfo;
    }

    @Override
    public Map technicalInfo() {
        Map technicalInfo = new HashMap();
        technicalInfo.put("language", "c++");
        return technicalInfo;
    }
}
