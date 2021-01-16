/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.dao;

import com.rolex.alphax.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author rolex
 * @since 2020
 */
@Mapper
public interface UserDao {

    List<User> findAll();

}
