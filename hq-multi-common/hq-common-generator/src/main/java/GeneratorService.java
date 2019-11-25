import java.util.List;

/**
 * @author: linliangkun
 * @date: 2019/11/25 0025
 * @description:生成Service与ServiceImpl
 */
public class GeneratorService {
    /**
     * 是否覆盖Service，Service一般只生成一次，因为有可能增加业务逻辑
     */
    private static boolean cover = false;

    public static void generatorServices(List<TableInfo> tableInfoList) {
        //首字母大写
        boolean isFirstUper = true;
        for (int i = 0; i < tableInfoList.size(); i++) {
            TableInfo tableInfo = tableInfoList.get(i);
            String tableName = tableInfo.getTableName();
            String className = GeneratorUtil.initTableName(tableName);
            String content = getServiceContent(className);
            FileDateUtil.writeBeanFile(className + "Service", content, GeneratorConstant.SERVICE_PACKAGE_NAME, cover);
        }
    }

    private static String getServiceContent(String className) {
        StringBuilder sb = new StringBuilder();
        sb.append("import " + GeneratorConstant.BASE_PACKAGE_NAME + ".BaseService;\n" +
                "import " + GeneratorConstant.ENTITY_PACKAGE_NAME + "." + className + ";\n" +
                "\n");
        sb.append(FileDateUtil.getAnnotation(null));
        sb.append("public interface " + className + "Service extends BaseService<" + className + "> {\n" +
                "}");
        return sb.toString();
    }

    public static void generatorServiceImpls(List<TableInfo> tableInfoList) {
        boolean isFirstUper = true;//首字母大写
        for (int i = 0; i < tableInfoList.size(); i++) {
            TableInfo tableInfo = tableInfoList.get(i);
            String tableName = tableInfo.getTableName();
            String className = GeneratorUtil.initTableName(tableName);
            String content = getServiceImplContent(className);
            FileDateUtil.writeBeanFile(className + "ServiceImpl", content, GeneratorConstant.SERVICE_IMPL_PACKAGE_NAME, cover);
        }
    }

    private static String getServiceImplContent(String className) {
        StringBuilder sb = new StringBuilder();
        sb.append("import " + GeneratorConstant.BASE_PACKAGE_NAME + ".AbstractBaseService;\n" +
                "import " + GeneratorConstant.ENTITY_PACKAGE_NAME + "." + className + ";\n" +
                "import " + GeneratorConstant.MAPPER_PACKAGE_NAME + "." + className + "Mapper;\n" +
                "import " + GeneratorConstant.SERVICE_PACKAGE_NAME + "." + className + "Service;\n" +
                "import org.springframework.stereotype.Service;\n");
        sb.append("import org.springframework.transaction.annotation.Transactional;");
        sb.append("\n\n");
        sb.append(FileDateUtil.getAnnotation(null));
        sb.append("@Service");
        sb.append("\n");
        sb.append("@Transactional(rollbackFor = Exception.class)");
        sb.append("\n");
        sb.append("public class " + className + "ServiceImpl extends AbstractBaseService<" + className + "Mapper, " + className + "> implements " + className + "Service {\n" +
                "\n" +
                "}");
        return sb.toString();
    }

}
