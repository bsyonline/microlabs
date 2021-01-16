/*
 * Copyright (C) 2019 bsyonline
 */
package com.rolex.alphax.dao;

import com.rolex.alphax.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author rolex
 * @since 2019
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
