package com.rolex.microlabs.ide.gateway.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.websocketx.Utf8FrameValidator;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

public class CustomWebSocketServerProtocolHandler extends WebSocketServerProtocolHandler {

    private final String websocketPath;
    private final String subprotocols;
    private final boolean allowExtensions;
    private final int maxFramePayloadLength;
    private final boolean allowMaskMismatch;


    public CustomWebSocketServerProtocolHandler(String websocketPath, String subprotocols,
            boolean allowExtensions) {
        this(websocketPath, subprotocols, allowExtensions, 65536, false);
        // TODO Auto-generated constructor stub
    }

    public CustomWebSocketServerProtocolHandler(String websocketPath,
            String subprotocols, boolean allowExtensions, int maxFrameSize,
            boolean allowMaskMismatch) {
        super(websocketPath, subprotocols, allowExtensions, maxFrameSize,
                allowMaskMismatch);

        this.websocketPath = websocketPath;
        this.subprotocols = subprotocols;
        this.allowExtensions = allowExtensions;
        maxFramePayloadLength = maxFrameSize;
        this.allowMaskMismatch = allowMaskMismatch;
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        ChannelPipeline cp = ctx.pipeline();
        if (cp.get(CustomWebSocketServerProtocolHandshakeHandler.class) == null) {
            // Add the WebSocketHandshakeHandler before this one.
            ctx.pipeline().addBefore(ctx.name(), CustomWebSocketServerProtocolHandshakeHandler.class.getName(),
                        new CustomWebSocketServerProtocolHandshakeHandler(websocketPath, subprotocols,
                                allowExtensions, maxFramePayloadLength, allowMaskMismatch));
        }
        if (cp.get(Utf8FrameValidator.class) == null) {
            // Add the UFT8 checking before this one.
            ctx.pipeline().addBefore(ctx.name(), Utf8FrameValidator.class.getName(),
                    new Utf8FrameValidator());
        }
    }

}