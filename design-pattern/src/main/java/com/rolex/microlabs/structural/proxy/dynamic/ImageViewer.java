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
        Image jpegImage1 = new PNGImage("sample/photo1.png");
        Image imageProxy1 = (Image) new ImageProxy(jpegImage1).getProxyInstance();
        imageProxy1.display();
    }
}