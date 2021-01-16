/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.dao.impl;

import com.rolex.alphax.dao.TxDao;
import com.rolex.alphax.model.TxMsg;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author rolex
 * @since 2020
 */
@Component
public class TxDaoImpl implements TxDao {
    @Override
    public int insert() {
        return 1;
    }

    @Override
    public TxMsg getById(String msgId) {
        TxMsg txMsg = new TxMsg();
        txMsg.setMsgId(UUID.randomUUID().toString());
        txMsg.setQueue("multi-queue1");
        txMsg.setMsgBody("hello");
        return txMsg;
    }

    @Override
    public void update(TxMsg txMsg) {

    }

    @Override
    public void delete(String msgId) {

    }
}
