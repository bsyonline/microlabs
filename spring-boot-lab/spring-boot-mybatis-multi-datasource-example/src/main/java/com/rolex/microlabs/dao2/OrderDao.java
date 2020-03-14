package com.rolex.microlabs.dao2;

import com.rolex.microlabs.model.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author rolex
 * @since 2020
 */
@Mapper
public interface OrderDao {

    Order findByOrderNo(Integer orderNo);

}
