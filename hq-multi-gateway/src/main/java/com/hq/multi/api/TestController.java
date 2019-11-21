package com.hq.multi.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linliangkun
 * @Title: TestController
 * @ProjectName hq-multi-parent
 * @Description: TODO
 * @date 2019/11/21 002111:37
 */
@RestController
public class TestController {
    @RequestMapping("/test01")
    public String test01() {
        return "哈哈哈";
    }

}
