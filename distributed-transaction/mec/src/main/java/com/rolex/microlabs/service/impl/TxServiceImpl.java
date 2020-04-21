/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.service.impl;

import com.rolex.microlabs.dao.TxDao;
import com.rolex.microlabs.model.TxMsg;
import com.rolex.microlabs.service.TxService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author rolex
 * @since 2020
 */
@Component
public class TxServiceImpl implements TxService {

    private static final int MAX_RETRY = 3;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    TxDao txDao;

    @Override
    public int preparedMsg(TxMsg txMsg) throws Exception {
        if (txMsg == null) {
            throw new RuntimeException("msg is null");
        }
        if (StringUtils.isEmpty(txMsg.getQueue())) {
            throw new RuntimeException("msg queue is null");
        }
        txMsg.setUpdatedTime(new Date());
        txMsg.setState(TxMsg.State.PREPARED);
        txMsg.setIsDead(TxMsg.DeadMsg.Normal.getVal());
        txMsg.setRetry(0);
        return txDao.insert();
    }

    @Override
    public void confirmAndSendMsg(String msgId) throws Exception {
        final TxMsg txMsg = txDao.getById(msgId);
        if (txMsg == null) {
            throw new RuntimeException("msg is null");
        }
        if (StringUtils.isEmpty(txMsg.getQueue())) {
            throw new RuntimeException("msg queue is null");
        }
        txMsg.setState(TxMsg.State.CONFIRM);
        txMsg.setUpdatedTime(new Date());
        txDao.update(txMsg);
        rabbitTemplate.setDefaultReceiveQueue(txMsg.getQueue());
        rabbitTemplate.convertAndSend(txMsg);
    }

    @Override
    public void sendMsg(TxMsg txMsg) throws Exception {
        txMsg.setState(TxMsg.State.CONFIRM);
        txMsg.setUpdatedTime(new Date());
        txMsg.setRetry(0);
        rabbitTemplate.setDefaultReceiveQueue(txMsg.getQueue());
        rabbitTemplate.convertAndSend(txMsg);
    }

    @Override
    public void resendMsg(TxMsg txMsg) throws Exception {
        txMsg.setState(TxMsg.State.CONFIRM);
        txMsg.setUpdatedTime(new Date());
        if (txMsg.getRetry() > MAX_RETRY) {
            txMsg.setIsDead(TxMsg.DeadMsg.Dead.getVal());
        }
        txMsg.setRetry(txMsg.getRetry() + 1);
        rabbitTemplate.setDefaultReceiveQueue(txMsg.getQueue());
        rabbitTemplate.convertAndSend(txMsg);
    }

    @Override
    public void resendMsg(String msgId) throws Exception {
        TxMsg txMsg = txDao.getById(msgId);
        if (txMsg == null) {
            throw new RuntimeException("msg is null");
        }
        if (StringUtils.isEmpty(txMsg.getQueue())) {
            throw new RuntimeException("msg queue is null");
        }
        txMsg.setState(TxMsg.State.CONFIRM);
        txMsg.setUpdatedTime(new Date());
        if (txMsg.getRetry() > MAX_RETRY) {
            txMsg.setIsDead(TxMsg.DeadMsg.Dead.getVal());
        }
        txMsg.setRetry(txMsg.getRetry() + 1);

        rabbitTemplate.setDefaultReceiveQueue(txMsg.getQueue());
        rabbitTemplate.convertAndSend(txMsg);
    }

    @Override
    public void markDeadMsg(String msgId) throws Exception {
        TxMsg txMsg = txDao.getById(msgId);
        if (txMsg == null) {
            throw new RuntimeException("msg is null");
        }
        if (StringUtils.isEmpty(txMsg.getQueue())) {
            throw new RuntimeException("msg queue is null");
        }
        txMsg.setIsDead(TxMsg.DeadMsg.Dead.getVal());
        txMsg.setUpdatedTime(new Date());
        txDao.update(txMsg);
    }

    @Override
    public TxMsg getMsg(String msgId) throws Exception {
        return txDao.getById(msgId);
    }

    @Override
    public void deleteMsg(String msgId) throws Exception {
        txDao.delete(msgId);
    }

    @Override
    public void resendDeadMsg(String queue, int count) throws Exception {
        List<TxMsg> list = pageForCondition(1, count);
        Map<String, TxMsg> map = new HashMap<>();
        if (list != null && !list.isEmpty()) {
            int totalPages = 100;
            for (TxMsg txMsg : list) {
                map.put(txMsg.getMsgId(), txMsg);
            }
            for (int page = 2; page < totalPages; page++) {
                list = pageForCondition(page, count);
                if (list != null && !list.isEmpty()) {
                    for (TxMsg txMsg : list) {
                        map.put(txMsg.getMsgId(), txMsg);
                    }
                }
            }
        }
        list = null;
        for (Map.Entry<String, TxMsg> kv : map.entrySet()) {
            final TxMsg txMsg = kv.getValue();
            txMsg.setUpdatedTime(new Date());
            txMsg.setRetry(txMsg.getRetry() + 1);
            txDao.update(txMsg);
            rabbitTemplate.setDefaultReceiveQueue(txMsg.getQueue());
            rabbitTemplate.convertAndSend(txMsg);
        }
    }

    @Override
    public List<TxMsg> pageForCondition(int page, int offset) throws Exception {
        return null;
    }
}
