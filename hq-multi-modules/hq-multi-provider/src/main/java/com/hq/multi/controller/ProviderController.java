package com.hq.multi.controller;

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
@RequestMapping("/provider2")
public class ProviderController {
    @RequestMapping("/test2")
    public String test() {
        return "OK";
    }
}
