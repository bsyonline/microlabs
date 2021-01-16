/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.trace;

import java.util.Stack;

/**
 * @author rolex
 * @since 2020
 */
public class TraceManager {
    private static final ThreadLocal<Stack<String>> trace = new ThreadLocal<Stack<String>>();

    private static String createSpan() {
        Stack<String> stack = trace.get();
        if (stack == null) {
            stack = new Stack<>();
            trace.set(stack);
        }
        String traceId;
        if (stack.isEmpty()) {
            traceId = TraceContext.getTraceId();
            if (traceId == null) {
                traceId = "nvl";
                TraceContext.setTraceId(traceId);
            }
        } else {
            traceId = stack.peek();
            TraceContext.setTraceId(traceId);
        }
        return traceId;
    }

    public static String createEntrySpan() {
        String span = createSpan();
        Stack<String> stack = trace.get();
        stack.push(span);
        return span;
    }

    public static String getExitSpan() {
        Stack<String> stack = trace.get();
        if (stack == null || stack.isEmpty()) {
            TraceContext.clear();
            return null;
        }
        return stack.pop();
    }

    public static String getCurrentSpan() {
        Stack<String> stack = trace.get();
        if (stack == null || stack.isEmpty()) {
            return null;
        }
        return stack.peek();
    }
}
