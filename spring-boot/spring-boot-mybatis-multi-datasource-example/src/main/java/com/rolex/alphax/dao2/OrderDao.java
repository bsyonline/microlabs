package com.rolex.alphax.dao2;

import com.rolex.alphax.model.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author rolex
 * @since 2020
 */
@Mapper
public interface OrderDao {

    Order findByOrderNo(Integer orderNo);

}
