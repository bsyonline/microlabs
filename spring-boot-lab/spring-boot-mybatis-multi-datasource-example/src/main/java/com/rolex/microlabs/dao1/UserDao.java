package com.rolex.microlabs.dao1;

import com.rolex.microlabs.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author rolex
 * @since 2020
 */
@Mapper
public interface UserDao {

    User findById(Integer id);

}
