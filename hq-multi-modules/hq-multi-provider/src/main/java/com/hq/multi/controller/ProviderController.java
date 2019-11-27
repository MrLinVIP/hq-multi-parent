package com.hq.multi.controller;


import com.hq.multi.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * @author linliangkun
 * @Title: ProviderController
 * @ProjectName hq-multi-parent
 * @Description: TODO
 * @date 2019/11/19 001917:32
 */
@RestController
@RequestMapping("/provider1")
public class ProviderController {
    @Autowired
    private RedisUtils redisUtils;
    @RequestMapping("/test2")
    public String test() {
        String t = redisUtils.get("test0");
        return t;
    }
}
