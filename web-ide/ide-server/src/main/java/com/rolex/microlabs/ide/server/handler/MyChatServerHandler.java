/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.ide.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author rolex
 * @since 2020
 */
@Slf4j
public class MyChatServerHandler extends ChannelInboundHandlerAdapter {


    static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    static ConcurrentMap<String, ChannelId> channelIdMap = new ConcurrentHashMap();

    public static void addChannel(Channel channel) {
        channels.add(channel);
        channelIdMap.put(channel.id().asShortText(), channel.id());
    }

    public static void removeChannel(Channel channel) {
        channels.remove(channel);
        channelIdMap.remove(channel.id().asShortText());
    }

    public static Channel findChannel(String id) {
        return channels.find(channelIdMap.get(id));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        addChannel(ctx.channel());
        System.out.println(ctx.channel().remoteAddress() + "上线了");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "下线了");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        String message = byteBuf.toString(CharsetUtil.UTF_8);
        log.info("ide server received msg from gateway: {}", message);
        String resp = "pong，" + message;
        String id = ctx.channel().id().asShortText();
        System.out.println("===id:" + id);
        findChannel(id).writeAndFlush(Unpooled.wrappedBuffer(resp.getBytes(CharsetUtil.UTF_8)));
        log.info("ide server reply msg to gateway: {}", resp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

}
