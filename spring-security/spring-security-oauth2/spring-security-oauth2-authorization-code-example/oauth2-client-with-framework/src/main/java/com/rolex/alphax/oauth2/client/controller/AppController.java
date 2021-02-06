package com.rolex.alphax.oauth2.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@Controller
public class AppController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/hello")
    public String hello(@RegisteredOAuth2AuthorizedClient("alphax") OAuth2AuthorizedClient authorizedClient,
                        @AuthenticationPrincipal OAuth2User principal,
                        Model model) {
        log.info("username={}", principal.getName());
        log.info("attributes={}", principal.getAttributes());
        log.info("authorities={}", principal.getAuthorities());
        log.info("clientScopes={}", authorizedClient.getClientRegistration().getScopes());
        log.info("clientName={}", authorizedClient.getClientRegistration().getClientName());
        model.addAttribute("username", principal.getName());
        return "welcome";
    }

}
