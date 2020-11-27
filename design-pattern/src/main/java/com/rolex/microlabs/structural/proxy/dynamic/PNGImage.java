/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.structural.proxy.dynamic;

/**
 * @author rolex
 * @since 2020
 */
public class PNGImage implements Image {
    private String imageFilePath;

    public PNGImage(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    @Override
    public void display() {
        System.out.println("display image " + imageFilePath);
    }
}
