

package com.hq.multi.dao;

import java.util.List;
import java.util.Map;

/**
 * @author: linliangkun
 * @date: 2019/11/28 0028
 * @description:数据库接口
 */
public interface GeneratorDao {
    List<Map<String, Object>> queryList(Map<String, Object> map);

    Map<String, String> queryTable(String tableName);

    List<Map<String, String>> queryColumns(String tableName);
}
