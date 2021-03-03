//package com.rolex.alphax.oauth2.client.config;
//
//import com.google.gson.Gson;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
//import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
//import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
//import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
//import org.springframework.security.oauth2.client.registration.ClientRegistration;
//import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
//import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
//import org.springframework.security.oauth2.client.web.AuthenticatedPrincipalOAuth2AuthorizedClientRepository;
//import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
//import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
//import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
//import org.springframework.security.oauth2.core.AuthorizationGrantType;
//import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Slf4j
//@Configuration
//public class OAuth2LoginConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////            http.authorizeRequests().antMatchers("/**", "/", "/error", "/webjars/**").permitAll()
////                    .anyRequest().authenticated()
////                    .and().logout().logoutUrl("/logout").logoutSuccessUrl("/")
////                    .and().oauth2Login(Customizer.withDefaults());
//        http
//                .oauth2Client()
//                .clientRegistrationRepository(clientRegistrationRepository())
//                .authorizedClientRepository(authorizedClientRepository())
//                .authorizedClientService(authorizedClientService())
//                .authorizationCodeGrant()
//                .authorizationRequestRepository(authorizationRequestRepository())
////                .authorizationRequestResolver(authorizationRequestResolver())
//                .accessTokenResponseClient(accessTokenResponseClient());
//    }
//
//    @Bean
//    public OAuth2AccessTokenResponseClient accessTokenResponseClient() {
//        return new DefaultAuthorizationCodeTokenResponseClient();
//    }
//
//    @Bean
//    public OAuth2AuthorizedClientRepository authorizedClientRepository() {
//        return new AuthenticatedPrincipalOAuth2AuthorizedClientRepository(authorizedClientService());
//    }
//
//    @Bean
//    public AuthorizationRequestRepository authorizationRequestRepository() {
//        return new HttpSessionOAuth2AuthorizationRequestRepository();
//    }
//
//    @Bean
//    public ClientRegistrationRepository clientRegistrationRepository() {
//        return new InMemoryClientRegistrationRepository(alphaXClientRegistration());
//    }
//
//
//    @Bean
//    public OAuth2AuthorizedClientService authorizedClientService() {
//        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository());
//    }
//
//    private ClientRegistration alphaXClientRegistration() {
//        ClientRegistration clientRegistration = ClientRegistration.withRegistrationId("alphax")
//                .clientId("1a6642b4-622d-11eb-a707-00059a3c7a00")
//                .clientSecret("123")
//                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")
//                .scope("read", "write")
//                .authorizationUri("http://www.alphax.com:8082/oauth/authorize")
//                .tokenUri("http://www.alphax.com:8082/oauth/token")
//                .userInfoUri("http://www.alphax.com:8081/users/info")
//                //.userNameAttributeName(IdTokenClaimNames.SUB)
//                //.jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
//                .clientName("AlphaX")
//                .build();
//        log.info("{}", new Gson().toJson(clientRegistration));
//        return clientRegistration;
//    }
//}
