package com.rolex.alphax.config;

import java.lang.annotation.*;

/**
 * @author rolex
 * @since 2020
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface DataSource {

}