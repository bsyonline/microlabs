/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.common.exception;

import com.rolex.alphax.common.IExceptionResponse;

/**
 * @author rolex
 * @since 2020
 */
public class BusinessException extends BaseException {

    private static final long serialVersionUID = 1L;

    public BusinessException(IExceptionResponse responseEnum, Object[] args, String message) {
        super(responseEnum, args, message);
    }

    public BusinessException(IExceptionResponse responseEnum, Object[] args, String message, Throwable cause) {
        super(responseEnum, args, message, cause);
    }
}
