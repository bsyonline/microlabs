/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.structural.proxy.static_proxy;

/**
 * @author rolex
 * @since 2020
 */
public class ImageProxy implements Image {

    private JPEGImage target;

    public ImageProxy(String imageFilePath) {
        this.target = new JPEGImage(imageFilePath);
    }

    @Override
    public void display() {
        System.out.println("proxy start");
        target.display();
        System.out.println("proxy end");
    }
}