/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author rolex
 * @since 2020
 */
@Data
@AllArgsConstructor
public class PointInfo {
    private String name;
    private Double longitude; // 经度
    private Double latitude; // 纬度
}
