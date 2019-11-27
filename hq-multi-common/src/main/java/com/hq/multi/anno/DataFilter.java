package com.hq.multi.anno;


import java.lang.annotation.*;


/**
 * @author: linliangkun
 * @date: 2019/11/25 0025
 * @description:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataFilter {

    /**
     * 是否允许用户平台为空
     * @return
     */
    boolean isUserEmpty() default true;
}
