/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rolex.microlabs.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author rolex
 * @since 2020
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

}
