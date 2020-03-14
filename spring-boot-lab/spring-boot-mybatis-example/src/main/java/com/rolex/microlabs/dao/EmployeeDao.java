package com.rolex.microlabs.dao;

import com.rolex.microlabs.model.Employee;
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
