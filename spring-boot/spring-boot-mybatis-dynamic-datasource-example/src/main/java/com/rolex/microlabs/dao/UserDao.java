/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.dao;

import com.rolex.microlabs.model.User;
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
