package com.hq.multi.fegin.fallback;

import com.hq.multi.fegin.ProviderService;
import com.hq.multi.result.R;
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

            @Override
            public R list() {
                return R.error("出错啦，老铁");
            }

        };
    }

}
