/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.structural.proxy.dynamic;

/**
 * @author rolex
 * @since 2020
 */
public class ImageViewer {
    public static void main(String[] args) {
        Image pngImage = new PNGImage("sample/photo1.png");
        Image imageProxy = (Image) new ImageProxy(pngImage).getProxyInstance();
        imageProxy.display();
    }
}