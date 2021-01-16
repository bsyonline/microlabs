package com.rolex.alphax.dao;

import com.rolex.alphax.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author rolex
 * @since 2019
 */
@Mapper
public interface UserMapper {

    List<User> findAll();

    List<User> findByPage();

    User findOne(int id);

    void update(User user);

    void insert(User user);

    void delete(User user);

}
