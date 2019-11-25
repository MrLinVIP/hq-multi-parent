import java.util.List;

/**
 * @author: linliangkun
 * @date: 2019/11/25 0025
 * @description:
 */
public class GeneratorXml {
    /**
     * 是否覆盖Mapper,Mapper一般只生成一次，因为有可能增加业务逻辑
     */
    private static boolean cover = false;

    public static void generatorXml(List<TableInfo> tableInfoList) {
        for (TableInfo tableInfo : tableInfoList) {
            String className = GeneratorUtil.initTableName(tableInfo.getTableName());
            String[] cols = tableInfo.getColNames();
            String[] types = tableInfo.getColTypes();
            String content = getMapperContent(className, cols, types);
            FileDateUtil.writexmlFile(className + "Mapper", content, GeneratorConstant.MAPPER_PACKAGE_NAME, cover);
        }
    }

    private static String getMapperContent(String className, String[] cols, String[] types) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<!DOCTYPE mapper\n" +
                "        PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"\n" +
                "        \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
        sb.append("\n<mapper namespace=\"" + GeneratorConstant.MAPPER_PACKAGE_NAME + "." + className + "Mapper" + "\">\r\n");
        sb.append("    <!-- 实体映射 -->\n" +
                "    <resultMap id=\"BaseResultMap\" type=\"" + GeneratorConstant.ENTITY_PACKAGE_NAME + "." + className + "\">\n");
        /*--------字段映射--------*/
        sb.append("    </resultMap>\n");
        sb.append("    <!-- 表基本列 -->\n" +
                "    <sql id=\"Base_Column_List\">");
        /*--------字段基本列------*/
        sb.append("</sql>\n");
        sb.append("\n</mapper>");
        return sb.toString();
    }
}
