import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 * @author: linliangkun
 * @date: 2019/11/25 0025
 * @description:根据表结构自动生成bean
 */
public class AutoCreateBean {

    public static void main(String[] args) throws Exception {
        List<String> tablesList = new ArrayList<String>();
        String[] tableArray = GeneratorConstant.TABLES.split(",");
        for (String table : tableArray) {
            if (table != null && table.trim().length() > 0) {
                tablesList.add(table);
            }
        }
        //获取数据库中的表列表数据信息
        List<TableInfo> list = getDbTableInfos(tablesList);
        if (list == null) {
            //log.error("数据库表为空==null");
            return;
        }
        //生成实体类型
        GeneratorEntity.generatorEntitys(list, getConnection());
        //生成基础类
        //GeneratorBaseClass.generatorBaseClass();
        //生成通用Mapper
        GeneratorMapper.generatorMappers(list);
        //生成service
        GeneratorService.generatorServices(list);
        //生成serviceImpl
        GeneratorService.generatorServiceImpls(list);
        //生成controller
        GeneratorController.generatorControllers(list);
        //生成xml
        GeneratorXml.generatorXml(list);
    }

    private static Connection getConnection() throws Exception {
        // 访问数据库 采用 JDBC方式
        Class.forName(GeneratorConstant.driverClassName);
        Properties props = new Properties();
        props.setProperty("user", GeneratorConstant.username);
        props.setProperty("password", GeneratorConstant.password);
        //设置可以获取remarks信息
        props.setProperty("remarks", "true");
        //设置可以获取tables remarks信息
        props.setProperty("useInformationSchema", "true");
        return DriverManager.getConnection(GeneratorConstant.dbUrl, props);
    }

    /**
     * 获取表结构信息
     *
     * @param tablesList 列表数据
     * @return 返回数据库表信息
     * @throws Exception 异常
     */
    private static List<TableInfo> getDbTableInfos(List<String> tablesList) throws Exception {
        Connection con = getConnection();
        DatabaseMetaData md = con.getMetaData();
        List<TableInfo> list = null;
        ResultSet rs = md.getTables(null, null, null, null);
        if (rs != null) {
            list = new ArrayList<TableInfo>();
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                if (tablesList == null || tablesList.size() == 0 || tablesList.contains(tableName)) {
                    list.add(new TableInfo(rs.getString("TABLE_NAME"), rs.getString("REMARKS")));
                }
            }
            rs.close();
        }
        con.close();
        return list;
    }
}
