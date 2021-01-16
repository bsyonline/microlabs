/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.common.exception;

import com.rolex.alphax.common.IExceptionResponse;
import com.rolex.alphax.common.enums.CommonExceptionEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author rolex
 * @since 2020
 */
@Setter
@Getter
public class BaseException extends RuntimeException {

    int code;
    String message;
    Object args;
    IExceptionResponse exceptionResponse;

    public BaseException(IExceptionResponse exceptionResponse, Object[] args, String message) {
        super();
        this.exceptionResponse = exceptionResponse;
        this.code = exceptionResponse.getCode();
        this.message = message;
        this.args = args;
    }

    public BaseException(IExceptionResponse exceptionResponse, Object[] args, String message, Throwable cause) {
        super(cause);
        this.exceptionResponse = exceptionResponse;
        this.code = exceptionResponse.getCode();
        this.message = message;
        this.args = args;
    }

    public BaseException(CommonExceptionEnum exceptionResponse) {
        super();
        this.exceptionResponse = exceptionResponse;
        this.code = exceptionResponse.getCode();
        this.message = exceptionResponse.getMessage();
    }

    public IExceptionResponse getExceptionResponse() {
        return exceptionResponse;
    }
}
