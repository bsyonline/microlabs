/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.structural.proxy.cglib;

/**
 * @author rolex
 * @since 2020
 */
public class ImageViewer {
    public static void main(String[] args) {
        GIFImage gifImage = new GIFImage("sample/photo1.png");
        GIFImage imageProxy = (GIFImage) new ImageProxy(gifImage).getProxyInstance();
		imageProxy.display();
    }
}