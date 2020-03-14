/*
 * Copyright (C) 2019 bsyonline
 */
package com.rolex.microlabs.dao;

import com.rolex.microlabs.model.User;
import org.apache.ibatis.annotations.*;
import sun.awt.SunHints;

import java.util.List;
import java.util.Map;

/**
 * @author rolex
 * @since 2019
 */
@Mapper // 和@MapperScan二选一
public interface UserDao {

    //    @Insert("insert into t_user (name, age, gender, skill) values (#{name}, #{age}, #{gender}, #{skill})")
    int save(User user);

    //    @Select("select id, name, age, gender, skill from t_user")
    List<User> findAll();

    List<User> findByCondition(User user);

    List<User> findByAnyCondition(User user);

    void update(User user);

    int batchSave(List<User> list);

    List<User> batchQuery(List<Integer> ids);

    int batchDelete(List<Integer> ids);

    void batchUpdate(List<User> list);

    List<User> listForPage();

    List<User> findByNameAndAge(@Param("name") String arg1, @Param("age") Integer arg2);

    List<User> findByAge(Integer age);

    List<Map<Integer,Integer>> groupByColumn(@Param("columnName") String columnName);

}
