package com.hq.multi.api;

import com.hq.multi.utils.RedisUtils;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("/test")
    public String test() {
        String t = redisUtils.get("test0");
        return t;
    }

}
