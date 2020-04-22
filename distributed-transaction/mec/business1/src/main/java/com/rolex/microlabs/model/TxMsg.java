/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author rolex
 * @since 2020
 */
@Setter
@Getter
public class TxMsg implements Serializable {
    String id;          // 主键
    String msgId;       // 消息id
    String msgBody;     // 消息体
    MsgType msgType;    // 消息类型
    String queue;       // 队列名称
    Date createdTime;   // 创建时间
    Date updatedTime;   // 最后更新时间
    int retry;          // 重试次数
    int isDead;         // 是否是死信消息
    State state;        // 状态
    String comment;     // 备注
    String backup1;     // 备用字段1
    String backup2;     // 备用字段2
    String backup3;     // 备用字段3

    public enum State {
        PREPARED(0),
        CONFIRM(1),

        ;
        int val;

        State(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }
    }

    public enum DeadMsg {
        Normal(0),
        Dead(-1);

        int val;

        DeadMsg(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }
    }

    public enum MsgType {
        TEXT(0),
        JSON(1);

        int val;

        MsgType(int val) {
            this.val = val;
        }
    }
}
