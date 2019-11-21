package com.hq.customer.fegin.fallback;

import com.hq.customer.fegin.ProviderService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author linliangkun
 * @Title: ProviderFallback
 * @ProjectName hq-multi-parent
 * @Description: TODO
 * @date 2019/11/20 002014:35
 */
@Component
public class ProviderFallback implements FallbackFactory<ProviderService>{
    @Override
    public ProviderService create(Throwable throwable) {
        return new ProviderService() {
            @Override
            public String test() {
                return "服务出现异常啦，请稍后再试哈。。。";
            }
        };
    }

}
