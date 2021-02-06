package com.rolex.alphax.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.rolex.alphax.model.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@Slf4j
public class AppController {

    @GetMapping("/oauth/code/alphax")
    public String callback(@RequestParam("code") String code, Model model) {
        log.info("code={}", code);
        // code换token
        String accessToken = getAccessToken(code);
        // token换userInfo
        SysUser userInfo = getUserInfo(accessToken);

        model.addAttribute("user", userInfo);
        return "redirect:/userPage?username=" + userInfo.getUsername();
    }

    @RequestMapping("/userPage")
    public String userPage(String username, Model model) {
        model.addAttribute("username", username);
        return "user";
    }

    @GetMapping("/oauth/test")
    public String user(Model model) {
        SysUser userInfo = new SysUser();
        userInfo.setUsername("abc");
        model.addAttribute("user", userInfo);
        return "test";
    }

    private String getAccessToken(String code) {
        String url = "http://localhost:8082/oauth/token" +
                "?client_id=1a6642b4-622d-11eb-a707-00059a3c7a00" +
                "&client_secret=123" +
                "&code=" + code +
                "&redirect_uri=http://localhost:8080/oauth/code/alphax" +
                "&grant_type=authorization_code";
        log.info("getAccessToken url:{}", url);
        // 构建请求头
        HttpHeaders requestHeaders = new HttpHeaders();
        // 指定响应返回json格式
        requestHeaders.add("accept", "application/json");
        // 构建请求实体
        HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);
        RestTemplate restTemplate = new RestTemplate();
        // post 请求方式
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
        String responseStr = response.getBody();
        log.info("responseStr={}", responseStr);

        // 解析响应json字符串
        JsonElement jsonElement = JsonParser.parseString(responseStr);
        String accessToken = jsonElement.getAsJsonObject().get("access_token").getAsString();
        log.info("accessToken={}", accessToken);
        return accessToken;
    }

    private SysUser getUserInfo(String accessToken) {
        String url = "http://localhost:8081/users/info?access_token=" + accessToken;
        log.info("getUserInfo url:{}", url);
        // 构建请求头
        HttpHeaders requestHeaders = new HttpHeaders();
        // 指定响应返回json格式
        requestHeaders.add("accept", "application/json");
        // AccessToken放在请求头中
        requestHeaders.add("Authorization", "token " + accessToken);
        // 构建请求实体
        HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);
        RestTemplate restTemplate = new RestTemplate();
        // get请求方式
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        String userInfo = response.getBody();
        log.info("userInfo={}", userInfo);
        return new Gson().fromJson(userInfo, SysUser.class);
    }
}
