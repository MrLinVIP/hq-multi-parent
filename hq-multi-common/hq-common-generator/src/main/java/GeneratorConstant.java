import java.io.IOException;
import java.util.Properties;

/**
 * @author: linliangkun
 * @date: 2019/11/25 0025
 * @description:生成表结构常量
 */
public class GeneratorConstant {

    static {
        Properties properties = new Properties();
        try {
            properties.load(GeneratorConstant.class.getResourceAsStream("/config.properties"));
            dbUrl = properties.getProperty("mysql.url");
            username = properties.getProperty("mysql.username");
            password = properties.getProperty("mysql.password");
            driverClassName = properties.getProperty("mysql.driverClassName");
            DATE_TIME_PATTERN = properties.getProperty("time.pattern");
            TABLES = properties.getProperty("mysql.tables");
            PACKAGE_NAME = properties.getProperty("mysql.package");
            COMMON_PACKAGE_NAME = properties.getProperty("mysql.common.package");
            MODEL_PATH = properties.getProperty("project.model.path");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 数据库配置
     */
    public static String dbUrl;
    public static String username;
    public static String password;
    public static String driverClassName;

    /**
     * 日期格式
     */
    public static String DATE_TIME_PATTERN;

    /**
     * 表名  用逗号隔开
     */
    public static String TABLES;


    /**
     * 包名根路径
     */
    public static String PACKAGE_NAME;
    /**
     * common包名根路径
     */
    public static String COMMON_PACKAGE_NAME;

    /**
     * 实体(Entity)包名
     */
    public static String ENTITY_PACKAGE_NAME = PACKAGE_NAME + ".entity";

    /**
     * 基础类(base)包名
     */
    public static String BASE_PACKAGE_NAME = COMMON_PACKAGE_NAME + ".base";

    /**
     * util包名
     */
    public static String UTIL_PACKAGE_NAME = COMMON_PACKAGE_NAME + ".util";

    /**
     * 映射类(mapper)包名
     */
    public static String MAPPER_PACKAGE_NAME = PACKAGE_NAME + ".mapper";

    /**
     * service包名
     */
    public static String SERVICE_PACKAGE_NAME = PACKAGE_NAME + ".service";

    /**
     * service实现类包名
     */
    public static String SERVICE_IMPL_PACKAGE_NAME = PACKAGE_NAME + ".service.impl";

    /**
     * controller包名
     */
    public static String CONTROLLER_PACKAGE_NAME = PACKAGE_NAME + ".controller";

    /**
     * constant包名
     */
    public static String CONSTANT_PACKAGE_NAME = PACKAGE_NAME + ".constant";

    /**
     * param包名
     */
    public static String PARAM_PACKAGE_NAME = PACKAGE_NAME + ".param";
    /**
     * 项目根目录
     */
    public static String ROOT_PATH = System.getProperty("user.dir");
    /**
     * 模块目录
     */
    public static String MODEL_PATH;
    /**
     * 代码路径
     */
    public static String CODE_PATH = ROOT_PATH + MODEL_PATH + "/src/main/java/";


}
