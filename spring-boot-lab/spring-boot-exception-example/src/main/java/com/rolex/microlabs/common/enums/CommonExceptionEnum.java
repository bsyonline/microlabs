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
public enum CommonExceptionEnum implements BusinessExceptionAssert {

    SERVER_ERROR(500, "Server internal error.");

    /**
     * 返回码
     */
    private int code;
    /**
     * 返回消息
     */
    private String message;
}
