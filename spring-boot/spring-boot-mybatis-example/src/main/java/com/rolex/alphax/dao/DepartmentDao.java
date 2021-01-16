package com.rolex.alphax.dao;

import com.rolex.alphax.model.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author rolex
 * @since 2020
 */
@Mapper
public interface DepartmentDao {
    List<Department> findAll();
}
