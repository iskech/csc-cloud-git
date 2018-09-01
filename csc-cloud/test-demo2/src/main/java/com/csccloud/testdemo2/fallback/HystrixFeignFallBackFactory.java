package com.csccloud.testdemo2.fallback;


import com.csccloud.testdemo2.fein.FeignTestDemo;
import feign.hystrix.FallbackFactory;

//
public class HystrixFeignFallBackFactory implements FallbackFactory<FeignTestDemo> {
    @Override
    public FeignTestDemo create(Throwable throwable) {
        return null;
    }
}
