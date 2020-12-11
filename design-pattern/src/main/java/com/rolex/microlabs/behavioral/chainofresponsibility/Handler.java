/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.behavioral.chainofresponsibility;

/**
 * @author rolex
 * @since 2020
 */
public abstract class Handler {
    private Handler next = null;

    public void handle() {
        if (next != null) {
            next.handle();
        }
    }

}
