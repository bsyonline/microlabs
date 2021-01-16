/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.licence.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * @author rolex
 * @since 2020
 */
@Setter
@Getter
public class LicenceAddRequest {
    @NotNull
    private Integer organizationId;
    @NotBlank
    private String licenceType;
    private String productName;
    @Min(value = 0)
    private Integer licenceMax;
    @PositiveOrZero
    private Integer licenceAllocated;
    private String comment;
}
