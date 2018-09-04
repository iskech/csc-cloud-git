package com.csccloud.testdemo.fallback;

import com.csccloud.testdemo.feign.FeignTestDemo;
import feign.hystrix.FallbackFactory;

//
public class HystrixFeignFallBackFactory implements FallbackFactory<FeignTestDemo> {
    //@Override
    public FeignTestDemo create(Throwable throwable) {
        return null;
    }
}
