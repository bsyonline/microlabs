package com.rolex.alphax.controller;

import com.google.common.collect.Lists;
import com.rolex.alphax.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    RestTemplate restTemplate;
    @Value("${security.oauth2.client.id}")
    String clientId;
    @Value("${security.oauth2.client.secret}")
    String secret;

    @GetMapping("/orders/list")
    public List list() {
        return Lists.newArrayList(new Order("001"));
    }

    @GetMapping("/oauth/code/callback")
    public List codeCallback(String code) {
        System.out.println(code);
        String url = "http://localhost:8082/oauth/token";
        String redirectUrl = "";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HashMap<String, Object> params = new HashMap<>();
        params.put("client_id", clientId);
        params.put("code", code);
        params.put("grant_type", "authorization_code");
        params.put("redirectUrl", redirectUrl);
        params.put("type", "authorization_code");
        params.put("secret", secret);
        HttpEntity httpEntity = new HttpEntity(params, headers);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        if (exchange.getStatusCodeValue()==200) {
            String body = exchange.getBody();
            System.out.println(body);
        }
        return Lists.newArrayList();
    }

    @GetMapping("/oauth/token/callback")
    public List tokenCallback(String code) {
        System.out.println(code);
        String url = "http://localhost:8082/oauth/token";
        String redirectUrl = "";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HashMap<String, Object> params = new HashMap<>();
        params.put("clientId", clientId);
        params.put("code", code);
        params.put("type", "authorization_code");
        params.put("redirectUrl", redirectUrl);
        params.put("type", "authorization_code");
        params.put("secret", secret);
        HttpEntity httpEntity = new HttpEntity(params, headers);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        if (exchange.getStatusCodeValue()==200) {
            String body = exchange.getBody();
            System.out.println(body);
        }
        return Lists.newArrayList();
    }
}
