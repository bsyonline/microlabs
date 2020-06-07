/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.netty.codec;

import com.rolex.microlabs.model.Msg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author rolex
 * @since 2020
 */
@Slf4j
public class MsgDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        log.info("byte to msg");
        int length = in.readInt();
        byte[] content = new byte[length];
        in.readBytes(content);
        out.add(new Msg(length, content));
    }
}
