/*
 * Copyright (C) 2018 bsyonline
 */
package com.rolex.alphax.model;

/**
 * @author rolex
 * @since 2018
 */
public class Customer {

    private CustomerType type;

    private int years;

    private int discount;

    public Customer(CustomerType type, int numOfYears) {
        super();
        this.type = type;
        this.years = numOfYears;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public enum CustomerType {
        /**
         * 个体
         */
        INDIVIDUAL,
        /**
         * 企业
         */
        BUSINESS;
    }
}
