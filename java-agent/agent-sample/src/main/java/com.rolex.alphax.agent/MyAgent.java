/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.agent;


import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * @author rolex
 * @since 2020
 */
public class MyAgent {
    public static void premain(String args, Instrumentation instrumentation) {
        System.out.println("premain start");
        System.out.println("agentArgs=" + args);
        instrumentation.addTransformer((ClassLoader loader, String className, Class<?> classBeingRedefined,
                                        ProtectionDomain protectionDomain, byte[] classfileBuffer) -> {
            if (loader != null) {
                System.out.println("className=" + className);
                System.out.println("loader=" + loader);
                System.out.println("classBeingRedefined=" + classBeingRedefined);
                System.out.println("protectionDomain=" + protectionDomain);
            }
            return classfileBuffer;
        }, true);
    }

    public static void premain(String args) {
        System.out.println("premain start");
        System.out.println(args);
    }
}
