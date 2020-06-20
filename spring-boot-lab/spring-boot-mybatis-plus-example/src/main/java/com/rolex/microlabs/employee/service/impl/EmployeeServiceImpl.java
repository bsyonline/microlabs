package com.rolex.microlabs.employee.service.impl;

import com.rolex.microlabs.employee.entity.Employee;
import com.rolex.microlabs.employee.mapper.EmployeeMapper;
import com.rolex.microlabs.employee.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rolex
 * @since 2020-06-20
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
