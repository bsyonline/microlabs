/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.trace;

import net.bytebuddy.asm.Advice;

import java.util.UUID;

/**
 * @author rolex
 * @since 2020
 */
public class TraceAdvice {

    @Advice.OnMethodEnter()
    public static void enter(@Advice.Origin("#t") String className, @Advice.Origin("#m") String methodName) {
        String traceId = TraceManager.getCurrentSpan();
        if (null == traceId) {
            traceId = UUID.randomUUID().toString();
            TraceContext.setTraceId(traceId);
        }
        String entrySpan = TraceManager.createEntrySpan();
        System.out.println("Tracing：" + entrySpan + " " + className + "." + methodName);

    }

    @Advice.OnMethodExit()
    public static void exit(@Advice.Origin("#t") String className, @Advice.Origin("#m") String methodName) {
        TraceManager.getExitSpan();
    }

}
