package com.rolex.alphax.dao;

import com.rolex.alphax.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author rolex
 * @since 2019
 */
public interface LocationRepository extends JpaRepository<Location, Integer> {
}
