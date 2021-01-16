package com.rolex.alphax.service;

import com.rolex.alphax.model.TxMsg;

import java.util.List;

/**
 * @author rolex
 * @since 2020
 */
public interface TxService {
    /**
     * 准备消息
     *
     * @param txMsg
     * @return
     * @throws Exception
     */
    int preparedMsg(TxMsg txMsg) throws Exception;

    /**
     * 确认并发送
     *
     * @param msgId
     * @throws Exception
     */
    void confirmAndSendMsg(String msgId) throws Exception;

    /**
     * 发送消息
     *
     * @param txMsg
     * @throws Exception
     */
    void sendMsg(TxMsg txMsg) throws Exception;

    /**
     * 重发消息
     *
     * @param txMsg
     * @throws Exception
     */
    void resendMsg(TxMsg txMsg) throws Exception;

    /**
     * 根据msgId重发
     *
     * @param msgId
     * @throws Exception
     */
    void resendMsg(String msgId) throws Exception;

    /**
     * 标记为死信消息
     *
     * @param msgId
     * @throws Exception
     */
    void markDeadMsg(String msgId) throws Exception;

    /**
     * 根据msgId获取消息
     *
     * @param msgId
     * @return
     * @throws Exception
     */
    TxMsg getMsg(String msgId) throws Exception;

    /**
     * 根据msgId删除消息
     *
     * @param msgId
     * @throws Exception
     */
    void deleteMsg(String msgId) throws Exception;

    /**
     * 批量发送死信消息
     *
     * @param queue
     * @param count
     * @throws Exception
     */
    void resendDeadMsg(String queue, int count) throws Exception;

    /**
     * @param page
     * @param offset
     * @return
     * @throws Exception
     */
    List<TxMsg> pageForCondition(int page, int offset) throws Exception;
}
