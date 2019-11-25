import java.util.List;

/**
 * 生成实体映射的Mapper
 *
 * @author chenzeke
 * @date 2018/11/22 18:27
 */
public class GeneratorMapper {
    /**
     * 是否覆盖Mapper,Mapper一般只生成一次，因为有可能增加业务逻辑
     */
    private static boolean cover = false;

    public static void generatorMappers(List<TableInfo> tableInfoList) {
        //首字母大写
        boolean isFirstUper = true;
        for (int i = 0; i < tableInfoList.size(); i++) {
            TableInfo tableInfo = tableInfoList.get(i);
            String tableName = tableInfo.getTableName();
            String className = GeneratorUtil.initTableName(tableName);
            String content = getMapperContent(className);
            FileDateUtil.writeBeanFile(className + "Mapper", content, GeneratorConstant.MAPPER_PACKAGE_NAME, cover);
        }
    }

    private static String getMapperContent(String className) {
        StringBuilder sb = new StringBuilder();
        sb.append("import " + GeneratorConstant.BASE_PACKAGE_NAME + ".BaseMapper;\n" +
                "import " + GeneratorConstant.ENTITY_PACKAGE_NAME + "." + className + ";\n");
        sb.append("import org.apache.ibatis.annotations.Mapper;" + "\n\n");
        sb.append(FileDateUtil.getAnnotation(null));
        sb.append("@Mapper" + "\n");
        sb.append("public interface " + className + "Mapper extends BaseMapper<" + className + "> {\n" +
                "}");
        return sb.toString();
    }

}
