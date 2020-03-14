/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.structural.proxy.static_proxy;

/**
 * @author rolex
 * @since 2020
 */
public class ImageViewer {
    public static void main(String[] args) {
		ImageProxy imageProxy1 = new ImageProxy("sample/photo1.jpeg");
		imageProxy1.display();
    }
}