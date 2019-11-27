package com.hq.customer.controller;

import com.hq.customer.fegin.ProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linliangkun
 * @Title: CustomerController
 * @ProjectName hq-multi-parent
 * @Description: TODO
 * @date 2019/11/19 001916:34
 */
@RestController
@RequestMapping("/customer")
@Slf4j
public class CustomerController {
    @Autowired
    private ProviderService providerService;

    @RequestMapping("/test")
    public String test() {

        return "OK";
    }
    @RequestMapping("/test2")
    public String test2() {
        //log.error("调用了一下");
        return providerService.test();

    }
}
