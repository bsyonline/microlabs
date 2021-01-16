package com.rolex.alphax.ide.gateway.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpMessage;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

public class SecurityServerHandler extends ChannelInboundHandlerAdapter {

    public static final AttributeKey<SecurityCheckComplete> SECURITY_CHECK_COMPLETE_ATTRIBUTE_KEY =
            AttributeKey.valueOf("SECURITY_CHECK_COMPLETE_ATTRIBUTE_KEY");

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpMessage) {
            //extracts device information headers
            HttpHeaders headers = ((FullHttpMessage) msg).headers();
            String customHeader = headers.get("Sec-WebSocket-Protocol");
            String host = headers.get("Host");
            String uuid = Objects.requireNonNull(customHeader);
            //deserialize device description
            System.out.println(uuid);
            //check ......
            int status = 0;
            if (uuid.equals("abc123")) {
                status = 200;
            }else{
                status = 401;
            }
            SecurityCheckComplete complete = new SecurityCheckComplete(uuid, status);
            ctx.channel().attr(SECURITY_CHECK_COMPLETE_ATTRIBUTE_KEY).set(complete);
            ctx.fireUserEventTriggered(complete);
        }
        super.channelRead(ctx, msg);
    }

    @Getter
    @AllArgsConstructor
    public static final class SecurityCheckComplete {
        private String connectionUUID;
        int status;
    }
}