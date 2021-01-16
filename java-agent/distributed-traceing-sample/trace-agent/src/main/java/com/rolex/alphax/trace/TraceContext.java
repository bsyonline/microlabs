/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.trace;

/**
 * @author rolex
 * @since 2020
 */
public class TraceContext {
    private static final ThreadLocal<String> traceLocal = new ThreadLocal<String>();

    public static void clear() {
        traceLocal.remove();
    }

    public static String getTraceId() {
        return traceLocal.get();
    }

    public static void setTraceId(String linkId) {
        traceLocal.set(linkId);
    }
}
