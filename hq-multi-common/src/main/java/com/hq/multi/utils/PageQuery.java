package com.hq.multi.utils;

import lombok.Getter;
import org.apache.commons.collections.MapUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: linliangkun
 * @date: 2019/11/26 0026
 * @description:分页查询参数
 */
@Getter
public class PageQuery extends HashMap<String, Object> {
    //当前页
    private static final String PAGENUM = "pageNum";
    //每页的数量
    private static final String PAGESIZE = "pageSize";
    //排序字段
    private static final String SIDX = "sidx";
    //排序方式
    private static final String SORT = "sort";

    private int pageNum;
    private int pageSize;
    private String pageOrder;

    private PageQuery() {
    }

    public static PageQuery build(Map<String, Object> pageParam, Map<String, Object> queryParam) {
        return init(pageParam, queryParam);
    }

    public static PageQuery build(Map<String, Object> pageParam) {
        return init(pageParam, null);
    }

    public static PageQuery buildQuery(Map<String, Object> queryParam) {
        return init(queryParam);
    }

    private static PageQuery init(Map<String, Object> pageParam, Map<String, Object> queryParam) {
        PageQuery pageQuery = new PageQuery();
        pageQuery.putAll(pageParam);
        pageQuery.pageNum = MapUtils.getInteger(pageParam, PAGENUM, 1);
        pageQuery.pageSize = MapUtils.getInteger(pageParam, PAGESIZE, 10);
//        pageQuery.startTime = MapUtils.getString(pageParam, STARTTIME, null);
        pageQuery.put(PAGENUM, pageQuery.pageNum);
        pageQuery.put(PAGESIZE, pageQuery.pageSize);

        String sidx = StringUtils.underscoreName(MapUtils.getString(pageParam, SIDX, ""));
        if (StringUtils.isNotBlank(sidx)) {
            String sort = (MapUtils.getString(pageParam, SORT, "ASC")).toUpperCase().contains("ASC") ? "ASC" : "DESC";
            pageQuery.pageOrder = sidx + " " + sort;
            pageQuery.put(SIDX, sidx);
            pageQuery.put(SORT, sort);
        }

        if (queryParam != null) {
            if(!(pageParam != null && pageParam.size()>0)){
                pageQuery.pageNum = MapUtils.getInteger(queryParam, PAGENUM, 1);
                pageQuery.pageSize = MapUtils.getInteger(queryParam, PAGESIZE, 10);
            }
            pageQuery.putAll(queryParam);
        }
        return pageQuery;
    }

    private static PageQuery init(Map<String, Object> queryParam) {
        PageQuery pageQuery = new PageQuery();
        if (queryParam != null) {
            pageQuery.putAll(queryParam);
            return pageQuery;
        }
        return pageQuery;
    }
}
