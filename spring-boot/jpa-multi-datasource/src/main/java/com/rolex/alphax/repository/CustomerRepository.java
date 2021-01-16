/*
 * Copyright (C) 2018 bsyonline
 */
package com.rolex.alphax.repository;

import com.rolex.alphax.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author rolex
 * @since 2018
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findByLastName(String lastName);

}