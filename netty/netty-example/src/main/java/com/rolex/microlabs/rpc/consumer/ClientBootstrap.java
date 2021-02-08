/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.rpc.consumer;

import com.rolex.microlabs.rpc.common.UserService;
import com.rolex.microlabs.rpc.netty.NettyClient;

/**
 * @author rolex
 * @since 2020
 */
public class ClientBootstrap {

    public static void main(String[] args) {
        NettyClient client = new NettyClient();
        UserService userService = (UserService) client.getInstance(UserService.class, "xyz");
        String result = userService.getUser("123");
        System.out.println("result=" + result);
    }
}
