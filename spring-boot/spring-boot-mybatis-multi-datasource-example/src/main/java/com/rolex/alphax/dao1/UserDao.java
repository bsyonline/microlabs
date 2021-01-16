package com.rolex.alphax.dao1;

import com.rolex.alphax.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author rolex
 * @since 2020
 */
@Mapper
public interface UserDao {

    User findById(Integer id);

}
