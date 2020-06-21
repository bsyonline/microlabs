/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.licence.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author rolex
 * @since 2020
 */
@Setter
@Getter
public class LicenceParam {
    @NotBlank(message = "licence type cannot be blank")
    private String licenceType;
}
