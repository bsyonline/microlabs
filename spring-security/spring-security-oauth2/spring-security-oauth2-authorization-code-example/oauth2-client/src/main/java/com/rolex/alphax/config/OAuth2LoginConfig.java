package com.rolex.alphax.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class OAuth2LoginConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**", "/", "/error", "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/")
                .and().oauth2Login();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        List<ClientRegistration> registrations = new ArrayList<>();
        registrations.add(alphaXClientRegistration());
        return new InMemoryClientRegistrationRepository(registrations);
    }

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService() {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository());
    }

    private ClientRegistration alphaXClientRegistration() {
        return ClientRegistration.withRegistrationId("alphax")
                .clientId("1a6642b4-622d-11eb-a707-00059a3c7a00")
                .clientSecret("123")
                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUriTemplate("{baseUrl}/oauth/code/{registrationId}")
                .scope("read", "write")
                .authorizationUri("http://localhost:8082/oauth/authorize")
                .tokenUri("http://localhost:8082/oauth/token")
                //.userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
                //.userNameAttributeName(IdTokenClaimNames.SUB)
                //.jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
                .clientName("AlphaX")
                .build();
    }
}
