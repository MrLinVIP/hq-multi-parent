package com.hq.multi.config;



import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: linliangkun
 * @date: 2019/11/25 0025
 * @description:
 */
@Data
@Configuration
@ConditionalOnExpression("!'${shiro.anon}'.isEmpty()")
@ConfigurationProperties(prefix = "com.hq.multi.shiro.anon")
public class FilterAnonConfig {
    private List<String> path = new ArrayList<>();
}
