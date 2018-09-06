package com.csccloud.cloudzullgateway.config;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//
//@Configuration
//public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
//
//   @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//
//
//    /**
//     * 覆写config方法
//     * @param clients
//     * @throws Exception
//     */
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        //自定义规则
//        clients.inMemory()
//                .withClient("client")
//                .resourceIds("user")
//                .authorizedGrantTypes("client_credentials", "refresh_token","password")
//                .scopes("webclient")
//                .secret("123456")
//                ;
//    }
//
//    /**
//     * 覆写config方法
//     * @param endpoints
//     * @throws Exception
//     */
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.authenticationManager(authenticationManager)
//                .userDetailsService(userDetailsService);
//    }
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//        //允许表单认证
//        oauthServer.allowFormAuthenticationForClients();
//    }
//
//}