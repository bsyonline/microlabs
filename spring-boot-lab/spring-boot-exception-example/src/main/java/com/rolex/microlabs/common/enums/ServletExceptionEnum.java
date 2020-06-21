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
public enum ServletExceptionEnum implements BusinessExceptionAssert {

    NoHandlerFoundException(7404, "No Handler Found."),
    HttpRequestMethodNotSupportedException(7405, "Http Request Method Not Supported."),
    HttpMediaTypeNotSupportedException(7406, "Http Media Type Not Supported."),
    MissingPathVariableException(7407, "Missing Path Variable."),
    MissingServletRequestParameterException(7408, "Missing Servlet Request Parameter."),
    TypeMismatchException(7409, "TypeMismatch."),
    HttpMessageNotReadableException(7410, "Http Message Not Readable."),
    HttpMessageNotWritableException(7411, "Http Message Not Writable."),
    HttpMediaTypeNotAcceptableException(7412, "Http Media Type Not Acceptable."),
    ServletRequestBindingException(7413, "Servlet Request Binding."),
    ConversionNotSupportedException(7414, "Conversion Not Supported."),
    MissingServletRequestPartException(7415, "Missing Servlet Request Part."),
    AsyncRequestTimeoutException(7416, "Async Request Timeout."),
    ;

    /**
     * 返回码
     */
    private int code;
    /**
     * 返回消息
     */
    private String message;
}
