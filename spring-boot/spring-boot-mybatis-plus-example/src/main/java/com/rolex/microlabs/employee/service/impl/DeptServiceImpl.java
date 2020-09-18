package com.rolex.microlabs.employee.service.impl;

import com.rolex.microlabs.employee.entity.Dept;
import com.rolex.microlabs.employee.mapper.DeptMapper;
import com.rolex.microlabs.employee.service.IDeptService;
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
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

}
