package com.rolex.microlabs.employee.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rolex.microlabs.employee.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rolex.microlabs.employee.service.bo.EmployeeBo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author rolex
 * @since 2020-06-20
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    Page<EmployeeBo> customPage(@Param("page") Page page,@Param("employee") Employee employee);
}
