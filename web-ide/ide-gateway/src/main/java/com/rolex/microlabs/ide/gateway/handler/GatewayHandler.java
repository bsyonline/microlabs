/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.ide.gateway.handler;

import com.rolex.microlabs.ide.gateway.client.IdeClient;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author rolex
 * @since 2020
 */
@Slf4j
public class GatewayHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    IdeClient ideClient = null;
    public static Map<String, Channel> channels = new ConcurrentHashMap<>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");
        super.channelActive(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        SecurityServerHandler.SecurityCheckComplete complete = null;
        System.out.println(evt + " " + evt.getClass());
        if (evt instanceof SecurityServerHandler.SecurityCheckComplete){
            log.info("Security check has passed");
            complete = (SecurityServerHandler.SecurityCheckComplete) evt;
            System.out.println(complete);
        }
        else if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
            log.info("Handshake has completed");
            complete = ctx.channel().attr(SecurityServerHandler.SECURITY_CHECK_COMPLETE_ATTRIBUTE_KEY).get();
            System.out.println(complete);
        }
        else if (evt instanceof WebSocketServerProtocolHandler.ServerHandshakeStateEvent) {
            log.info("Handshake has completed");
            complete = ctx.channel().attr(SecurityServerHandler.SECURITY_CHECK_COMPLETE_ATTRIBUTE_KEY).get();
            System.out.println(complete);
        }
        if((complete!=null && complete.getStatus()==200)){
            super.userEventTriggered(ctx, evt);
        }else {
            ctx.channel().writeAndFlush(new TextWebSocketFrame("error"));
            ctx.close();
            return ;
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("channelRead0");
        String text = msg.text();
        log.info("gateway received msg from client: {}", text);
        if (ideClient == null) {
            String host = "localhost";
            int port = 8888;
            ideClient = new IdeClient(host, port);
            ideClient.connect();
            log.info("ide client connected to server: http://{}:{}", host, port);
        }
        channels.put("abc", ctx.channel());
        ideClient.send(text);
    }
}
