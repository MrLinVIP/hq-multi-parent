package com.hq.multi.anno;

import java.lang.annotation.*;

/**
 * @author: linliangkun
 * @date: 2019/11/25 0025
 * @description:指定字段所使用的es分词器与字段类型
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Analyzer {
    /**
     * 指定索引类型
     * ik_smart 粗粒度分词           ==>  搜索使用粗粒度分词
     * ik_max_word 细粒度分词    ==> 索引使用细粒度分词
     * standard  标准分词
     *
     * @return
     */
    String analyzer() default "";

    /**
     * 索引字段类型
     * keyword  :    存储数据时候，不会分词建立索引
     * text         :    存储数据时候，会自动分词，并生成索引
     *
     * @return type
     */
    String type() default "text";
}
