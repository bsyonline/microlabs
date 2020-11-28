/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.creational.builder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author rolex
 * @since 2020
 */
@Setter
@Getter
@AllArgsConstructor
public class Weapon {
    String name;
    int level;
    Profession[] profession;
}
