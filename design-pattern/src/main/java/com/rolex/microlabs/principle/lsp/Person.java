/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.principle.lsp;

/**
 * @author rolex
 * @since 2020
 */
public class Person extends Animal {
    Animal animal;

    void set(Animal animal) {
        this.animal = animal;
    }

    void play() {
        animal.shout();
    }

}
