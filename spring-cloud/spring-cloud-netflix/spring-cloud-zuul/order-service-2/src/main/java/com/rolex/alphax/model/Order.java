/*
 * Copyright (C) 2019 bsyonline
 */
package com.rolex.alphax.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rolex
 * @since 2019
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    String id;
    String date;
    String type;

}
