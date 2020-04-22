package com.rolex.microlabs.dao;

import com.rolex.microlabs.model.TxMsg;

/**
 * @author rolex
 * @since 2020
 */
public interface TxDao {
    int insert();

    TxMsg getById(String msgId);

    void update(TxMsg txMsg);

    void delete(String msgId);
}
