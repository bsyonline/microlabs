/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.ide.gateway.handler;

import ch.qos.logback.core.net.SyslogOutputStream;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;
import lombok.extern.slf4j.Slf4j;

import javax.sound.midi.Soundbank;

/**
 * @author rolex
 * @since 2020
 */
@Slf4j
public class MyChatClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        String resp = byteBuf.toString(CharsetUtil.UTF_8);
        log.info("ide client received msg from server: {}", resp);
        Channel channel = GatewayHandler.channels.get("abc");
        channel.writeAndFlush(new TextWebSocketFrame(resp));
        log.info("ide client reply to browser: {}", resp);
    }

}
