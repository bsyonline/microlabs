/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.structural.proxy.cglib;

/**
 * @author rolex
 * @since 2020
 */
public class App {
    public static void main(String[] args) {
        GIFImage gifImage = new GIFImage("photo1.gif");
        GIFImage imageProxy = (GIFImage) new ImageProxy(gifImage).getProxyInstance();
		imageProxy.display();
    }
}