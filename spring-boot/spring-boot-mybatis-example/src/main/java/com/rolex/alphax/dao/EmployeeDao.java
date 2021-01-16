package com.rolex.alphax.dao;

import com.rolex.alphax.model.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author rolex
 * @since 2020
 */
@Mapper
public interface EmployeeDao {

    List<Employee> findAll();

}
