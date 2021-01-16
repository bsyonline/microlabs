/*
 * Copyright (C) 2021 bsyonline
 */
package com.rolex.alphax.controller;

import com.google.gson.Gson;
import com.rolex.alphax.model.RequestMessage;
import com.rolex.alphax.model.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author rolex
 * @since 2021
 */
@Controller
@Slf4j
public class UserController {
    // 收到消息记数
    private AtomicInteger count = new AtomicInteger(0);

    /**
     * @param requestMessage
     * @return
     * @MessageMapping 指定要接收消息的地址，类似@RequestMapping。除了注解到方法上，也可以注解到类上
     * @SendTo默认 消息将被发送到与传入消息相同的目的地
     * 消息的返回值是通过{@link org.springframework.messaging.converter.MessageConverter}进行转换
     */
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public ResponseMessage broadcast(RequestMessage requestMessage) throws InterruptedException {
        log.info("receive message = {}", new Gson().toJson(requestMessage));
        ResponseMessage responseMessage = new ResponseMessage();
        Thread.sleep(1000); // simulated delay
        responseMessage.setResult("Hello, " + HtmlUtils.htmlEscape(requestMessage.getName()) + "!");
        return responseMessage;
    }

}
