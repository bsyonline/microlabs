/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax;

import com.rolex.alphax.model.TxMsg;
import com.rolex.alphax.service.TxService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author rolex
 * @since 2020
 */
@SpringBootApplication
@RestController

public class BusinessApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusinessApplication.class, args);
    }

    @Reference(version = "1.0.0")
    private TxService txService;

    @RequestMapping("/test")
    public String test() throws Exception {
        TxMsg txMsg = new TxMsg();
        txMsg.setMsgId(UUID.randomUUID().toString());
        txMsg.setQueue("multi-queue1");
        txMsg.setMsgBody("hello");
        int result = txService.preparedMsg(txMsg);
        if (result == 1) {
            System.out.println("do something...");
            txService.confirmAndSendMsg(txMsg.getMsgId());
            return "success";
        }
        return "error";
    }
}
