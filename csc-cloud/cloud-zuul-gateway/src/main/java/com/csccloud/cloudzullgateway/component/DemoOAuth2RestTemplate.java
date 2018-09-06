//package com.csccloud.cloudzullgateway.component;
//
//import lombok.extern.log4j.Log4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.oauth2.client.OAuth2RestTemplate;
//import org.springframework.stereotype.Component;
//
////@Component
//@Log4j
//public class DemoOAuth2RestTemplate {
//   // @Autowired
//    private OAuth2RestTemplate oAuth2RestTemplate;
//public String getUser(String name){
//    log.debug("in demooauth2:{}"+name);
//    ResponseEntity<String> exchange = oAuth2RestTemplate.exchange
//            ("http://localhost:9000/api/test/testUpdate?name={name}", HttpMethod.POST, null, String.class, name);
//    return exchange.getBody();
//}
//}
