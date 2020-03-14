/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.structural.proxy.static_proxy;

/**
 * @author rolex
 * @since 2020
 */
public class JPEGImage implements Image {
    String imageFilePath;

    public JPEGImage(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    @Override
    public void display() {
        System.out.println("display image" + imageFilePath);
    }
}
