package com.rolex.alphax.oauth2.client.controller;

import com.rolex.alphax.oauth2.client.model.PrincipalDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Slf4j
@Controller
public class AppController {

    /*@Autowired
    private OAuth2AuthorizedClientService clientService;
    @Autowired
    RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .interceptors(new ClientHttpRequestInterceptor() {
                    @Override
                    public ClientHttpResponse intercept(HttpRequest httpRequest,
                                                        byte[] bytes,
                                                        ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
                        OAuth2AuthenticationToken token = OAuth2AuthenticationToken.class.cast(
                                SecurityContextHolder.getContext().getAuthentication()
                        );

                        OAuth2AuthorizedClient oAuth2AuthorizedClient = clientService.loadAuthorizedClient(token.getAuthorizedClientRegistrationId(),
                                token.getName());

                        httpRequest.getHeaders().add(HttpHeaders.AUTHORIZATION, *//*"Bearer " +*//* oAuth2AuthorizedClient.getAccessToken().getTokenValue());
                        return clientHttpRequestExecution.execute(httpRequest, bytes);
                    }
                }).build();
    }*/

    /*@RequestMapping("/hello")
    public PrincipalDetails index(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) {
        OAuth2AuthorizedClient oAuth2AuthorizedClient = clientService.loadAuthorizedClient(authorizedClient.getClientRegistration().getRegistrationId(),
                authorizedClient.getPrincipalName());
        String uri = oAuth2AuthorizedClient.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUri()+"?access_token="+authorizedClient.getAccessToken().getTokenValue();

        ResponseEntity<PrincipalDetails> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, null, PrincipalDetails.class);
        return responseEntity.getBody();
    }*/

    @RequestMapping("/hello")
    public String hello(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
                        @AuthenticationPrincipal OAuth2User principal,
                        Model model) {
        log.info("username={}", principal.getName());
        log.info("username={}", SecurityContextHolder.getContext().getAuthentication().getName());
        log.info("attributes={}", principal.getAttributes());
        log.info("authorities={}", principal.getAuthorities());
        log.info("clientScopes={}", authorizedClient.getClientRegistration().getScopes());
        log.info("clientName={}", authorizedClient.getClientRegistration().getClientName());
        model.addAttribute("username", principal.getName());
        return "welcome";
    }

}
