/*
 * Copyright (C) 2021 bsyonline
 */
package com.rolex.alphax.heandler;

import com.rolex.alphax.config.HttpSessionConfigurator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author rolex
 * @since 2021
 */
@Component
@Slf4j
@ServerEndpoint(value = "/ws", configurator = HttpSessionConfigurator.class)
public class WsServerHandler {

    /*每个浏览器连接都会有一个新的会话对象*/
    private Session session;
    /*用来存储每个会话的session,静态的不会被实例化*/
    private static CopyOnWriteArraySet<WsServerHandler> webSocketSets = new CopyOnWriteArraySet<>();

    private static WsServerHandler wsServerHandler;

    @PostConstruct
    public void init() {
        wsServerHandler = this;
    }

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        log.info("session:{}", config.getUserProperties().get("sessionid"));
        this.session = session;
        webSocketSets.add(this);
        HttpSession httpSession = (HttpSession) config.getUserProperties().get("sessionid");
        System.out.println(httpSession.getAttribute("name"));
        //sessionMap.put(session.getId(), session);
        String id = session.getId();
        log.info("id={}, httpSession={}", id, httpSession);
        log.info("WebSocket连接建立");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        log.info("收到消息: {}", message);
        session.getBasicRemote().sendText("hello, " + message);
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        webSocketSets.remove(this);
        log.info("WebSocket连接断开");
        session.close();
    }

    @OnError
    public void onError(Session sess, Throwable e) {
        webSocketSets.remove(this);
        Throwable cause = e.getCause();
        /* normal handling... */
        if (cause != null) {
            System.out.println("Error-info: cause->" + cause);
        }
        try {
            // Likely EOF (i.e. user killed session)
            // so just Close the input stream as instructed
            sess.close();
        } catch (IOException ex) {
            System.out.println("Handling eof, A cascading IOException was caught: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            System.out.println("Session error handled. (likely unexpected EOF) resulting in closing User Session.");

        }
    }
}
