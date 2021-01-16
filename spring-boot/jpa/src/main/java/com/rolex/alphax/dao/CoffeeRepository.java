package com.rolex.alphax.dao;

import com.rolex.alphax.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author rolex
 * @since 2019
 */
public interface CoffeeRepository extends JpaRepository<Coffee, Integer> {
}
