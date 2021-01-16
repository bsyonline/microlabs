/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rolex.alphax.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author rolex
 * @since 2020
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

}
