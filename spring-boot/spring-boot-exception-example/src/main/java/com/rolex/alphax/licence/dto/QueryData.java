/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.licence.dto;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rolex.alphax.licence.entity.Licence;

/**
 * @author rolex
 * @since 2020
 */
public class QueryData<T> {
    public QueryData(IPage<Licence> page, T t) {
    }
}
