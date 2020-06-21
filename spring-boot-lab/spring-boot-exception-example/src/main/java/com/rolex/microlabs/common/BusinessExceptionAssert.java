/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.common;

import com.rolex.microlabs.common.exception.BaseException;
import com.rolex.microlabs.common.exception.BusinessException;

import java.text.MessageFormat;

/**
 * @author rolex
 * @since 2020
 */
public interface BusinessExceptionAssert extends IExceptionResponse, Assert {

    @Override
    default BaseException newException(Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);

        return new BusinessException(this, args, msg);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);

        return new BusinessException(this, args, msg, t);
    }

}
