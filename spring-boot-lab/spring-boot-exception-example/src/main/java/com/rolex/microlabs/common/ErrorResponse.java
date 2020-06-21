/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author rolex
 * @since 2020
 */
@Setter
@Getter
@AllArgsConstructor
public class ErrorResponse {
    int code;
    String message;
}
