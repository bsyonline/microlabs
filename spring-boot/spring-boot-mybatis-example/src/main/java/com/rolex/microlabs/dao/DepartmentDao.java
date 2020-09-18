package com.rolex.microlabs.dao;

import com.rolex.microlabs.model.Department;
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
