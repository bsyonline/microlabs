/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.ide.gateway.client;

import com.rolex.microlabs.ide.gateway.handler.MyChatClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author rolex
 * @since 2020
 */
@Slf4j
public class IdeClient {

    private final String host;
    private final int port;
    Channel channel;
    EventLoopGroup group;
    ChannelFuture channelFuture;

    public IdeClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new MyChatClientHandler());
                    }
                });
        ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
        channel = channelFuture.channel();
    }

    public void send(String msg){
        channel.writeAndFlush(Unpooled.wrappedBuffer(msg.getBytes()));
        log.info("ide client send msg to server: {}", msg);
    }

    public void close() {
        try {
            channelFuture.channel().close();
        } finally {
            group.shutdownGracefully();
        }
    }

}
