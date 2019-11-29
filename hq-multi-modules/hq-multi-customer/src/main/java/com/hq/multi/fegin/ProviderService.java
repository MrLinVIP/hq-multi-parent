package com.hq.multi.fegin;

import com.hq.multi.fegin.fallback.ProviderFallback;
import com.hq.multi.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author linliangkun
 * @Title: ProviderService
 * @ProjectName hq-multi-parent
 * @Description: TODO
 * @date 2019/11/19 001915:21
 */
@FeignClient(value = "hq-multi-provider", fallbackFactory = ProviderFallback.class)
@RequestMapping("/api/provider")
public interface ProviderService {
    @GetMapping("/test")
    String test();

    @GetMapping("/list")
    R list();
}
