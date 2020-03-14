/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.structural.proxy.cglib;

/**
 * @author rolex
 * @since 2020
 */
public class GIFImage {
    String imageFilePath;

    public GIFImage(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    public void display() {
        System.out.println("display image" + imageFilePath);
    }
}
