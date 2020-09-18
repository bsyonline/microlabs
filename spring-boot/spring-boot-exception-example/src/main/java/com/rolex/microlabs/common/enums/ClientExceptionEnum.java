/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.common.enums;

import com.rolex.microlabs.common.BusinessExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author rolex
 * @since 2020
 */
@Getter
@AllArgsConstructor
public enum ClientExceptionEnum implements BusinessExceptionAssert {


    VALID_ERROR(7401, "Invalid Params");
    /**
     * 返回码
     */
    private int code;
    /**
     * 返回消息
     */
    private String message;
}
