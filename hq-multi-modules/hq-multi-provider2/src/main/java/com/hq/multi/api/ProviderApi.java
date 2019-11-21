package com.hq.multi.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linliangkun
 * @Title: ProviderController
 * @ProjectName hq-multi-parent
 * @Description: TODO
 * @date 2019/11/19 001916:22
 */
@RestController
@RequestMapping("/api/provider")
public class ProviderApi {

    @GetMapping("/test")
    public String test() {
        return "调用成功<2>！";
    }

}
