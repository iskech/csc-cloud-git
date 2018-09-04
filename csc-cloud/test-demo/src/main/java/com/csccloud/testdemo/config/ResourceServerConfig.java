package com.csccloud.testdemo.config;

import org.springframework.context.annotation.Configuration;

////@Configuration
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//    /**
//     * 覆写访问保护资源规则
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http    .authorizeRequests().antMatchers("/api/test/feign").hasRole("ADMIN")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/user/**").permitAll();;
//    }
//
//}
