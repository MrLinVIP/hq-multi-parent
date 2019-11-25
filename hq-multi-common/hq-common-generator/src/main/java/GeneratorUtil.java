/**
 * 生成工具类
 *
 * @author zhouyibin
 * @date 2018/11/22 18:27
 */
public class GeneratorUtil {
    /**
     * 是否覆盖Service，Service一般只生成一次，因为有可能增加业务逻辑
     */
    private static boolean cover = false;

    public void generatorUtils() {
        String dateUtil = getDateUtilContent();
        FileDateUtil.writeBeanFile("DateUtil", dateUtil, GeneratorConstant.UTIL_PACKAGE_NAME, cover);

        String stringUtil = getStringUtilContent();
        FileDateUtil.writeBeanFile("StringUtil", stringUtil, GeneratorConstant.UTIL_PACKAGE_NAME, cover);

        String R = getRContent();
        FileDateUtil.writeBeanFile("R", R, GeneratorConstant.UTIL_PACKAGE_NAME, cover);

        String msgCode = getMsgCodeContent();
        FileDateUtil.writeBeanFile("MsgCode", msgCode, GeneratorConstant.CONSTANT_PACKAGE_NAME, cover);

    }

    public static String initTableName(String entityName) {
        String tableName = "";
        String[] strings = entityName.split("_");
        String cut = strings[0].substring(1).toLowerCase();
        String substring = strings[0].substring(0, 1);
        String headName = substring.toUpperCase() + cut;
        for (String word : strings) {
            String lowerCase = word.substring(1).toLowerCase();
            String head = word.substring(0, 1).toUpperCase();
            String compl = head + lowerCase;
            tableName += compl;
        }
        return tableName;
    }

    public static String lowCase(String str) {
        String cut = str.substring(1);
        String head = str.substring(0, 1).toLowerCase();
        return head + cut;
    }

    private static String getDateUtilContent() {
        StringBuilder sb = new StringBuilder();
        sb.append("import java.text.SimpleDateFormat;\n" +
                "import java.util.Date;\n\n");
        sb.append(FileDateUtil.getAnnotation("日期工具类"));
        sb.append("public class DateUtil {\n" +
                "\n" +
                "    public static final String DATE_TIME_PATTERN = \"yyyy-MM-dd HH:mm:ss\";\n" +
                "    public static final String MINUTE_PATTERN = \"yyyy-MM-dd HH:mm\";\n" +
                "    public static final String HOUR_PATTERN = \"yyyy-MM-dd HH\";\n" +
                "    public static final String DATE_PATTERN = \"yyyy-MM-dd\";\n" +
                "    public static final String MONTH_PATTERN = \"yyyy-MM\";\n" +
                "    public static final String YEAR_PATTERN = \"yyyy\";\n" +
                "\n" +
                "    /**\n" +
                "     * 日期格式化\n" +
                "     *\n" +
                "     * @param date    日期时间\n" +
                "     * @param pattern 日期格式\n" +
                "     * @return 返回日期格式化后的字符串\n" +
                "     */\n" +
                "    public static String dateFormat(Date date, String pattern) {\n" +
                "        if (date == null) {\n" +
                "            //默认当前时间\n" +
                "            date = new Date();\n" +
                "        }\n" +
                "        if (pattern == null) {\n" +
                "            // 默认日期格式化\n" +
                "            pattern = DATE_TIME_PATTERN;\n" +
                "        }\n" +
                "        SimpleDateFormat sdf = new SimpleDateFormat(pattern);\n" +
                "        return sdf.format(date);\n" +
                "    }\n" +
                "\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(dateFormat(new Date(), DATE_PATTERN));\n" +
                "    }\n" +
                "}");
        return sb.toString();
    }

    private static String getStringUtilContent() {
        StringBuilder sb = new StringBuilder();
        sb.append(FileDateUtil.getAnnotation("字符串工具类"));
        sb.append("public class StringUtil {\n" +
                "\n" +
                "    /**\n" +
                "     * 表示对象为空或长度为0的String、空格字符串(空白的)\n" +
                "     *\n" +
                "     * @param str 字符串\n" +
                "     * @return 返回true/false\n" +
                "     */\n" +
                "    public static boolean isBlank(String str) {\n" +
                "        return (str == null || \"\".equals(str.trim()));\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * 表示对象为空或长度为0的String(空)\n" +
                "     *\n" +
                "     * @param str 字符串\n" +
                "     * @return 返回true/false\n" +
                "     */\n" +
                "    public static boolean isEmpty(String str) {\n" +
                "        return (str == null || \"\".equals(str));\n" +
                "    }\n" +
                "}");
        return sb.toString();
    }

    private static String getRContent() {
        StringBuilder sb = new StringBuilder();
        sb.append("import " + GeneratorConstant.PACKAGE_NAME + ".constant.MsgCode;\n" +
                "import io.swagger.annotations.ApiModel;\n" +
                "import io.swagger.annotations.ApiModelProperty;\n" +
                "import lombok.Data;\n" +
                "import lombok.experimental.Accessors;\n" +
                "\n" +
                "import java.io.Serializable;\n\n");
        sb.append(FileDateUtil.getAnnotation("返回实体，映射成JSON"));
        sb.append("@ApiModel(value = \"返回实体\")\n" +
                "@Data\n" +
                "@Accessors(chain = true)\n" +
                "public class R<T> implements Serializable {\n" +
                "    /**\n" +
                "     * 状态码\n" +
                "     */\n" +
                "    @ApiModelProperty(value = \"状态码\", name = \"code\")\n" +
                "    private Integer code;\n" +
                "    /**\n" +
                "     * 提示消息\n" +
                "     */\n" +
                "    @ApiModelProperty(value = \"提示消息\", name = \"msg\")\n" +
                "    private String msg;\n" +
                "    /**\n" +
                "     * 实体数据\n" +
                "     */\n" +
                "    @ApiModelProperty(value = \"实体数据\", name = \"data\")\n" +
                "    private T data;\n" +
                "\n" +
                "    public R() {\n" +
                "        this.code = MsgCode.SUCCESS.getCode();\n" +
                "        this.msg = MsgCode.SUCCESS.getMessage();\n" +
                "    }\n" +
                "\n" +
                "    public R(T data) {\n" +
                "        this.data = data;\n" +
                "        this.code = MsgCode.SUCCESS.getCode();\n" +
                "        this.msg = MsgCode.SUCCESS.getMessage();\n" +
                "    }\n" +
                "\n" +
                "    public R(T data, String msg) {\n" +
                "        this.data = data;\n" +
                "        this.code = MsgCode.SUCCESS.getCode();\n" +
                "        this.msg = msg;\n" +
                "    }\n" +
                "\n" +
                "    public R(T data, MsgCode msgCode) {\n" +
                "        this.data = data;\n" +
                "        this.code = msgCode.getCode();\n" +
                "        this.msg = msgCode.getMessage();\n" +
                "    }\n" +
                "\n" +
                "    public R(int code, String message) {\n" +
                "        this.code = code;\n" +
                "        this.msg = message;\n" +
                "    }\n" +
                "\n" +
                "    public R(MsgCode msgCode) {\n" +
                "        this.code = msgCode.getCode();\n" +
                "        this.msg = msgCode.getMessage();\n" +
                "    }\n" +
                "}");
        return sb.toString();
    }

    private static String getMsgCodeContent() {
        StringBuilder sb = new StringBuilder();
        sb.append(FileDateUtil.getAnnotation("消息状态码统一定义"));
        sb.append("public enum MsgCode {\n" +
                "    /**\n" +
                "     * 状态码与提示语对应\n" +
                "     */\n" +
                "    SUCCESS(200, \"成功\"),\n" +
                "    ILLEGAL_ARGUMENT(400, \"参数异常\"),\n" +
                "    NO_AUTH(401, \"没有权限\"),\n" +
                "    NOT_FOUNT(404, \"无法找到资源\"),\n" +
                "    NO_LOGIN(403, \"未登录\"),\n" +
                "    ServerException(500, \"服务器繁忙\"),\n" +
                "    NOT_NULL(1000, \"参数不能为空\"),\n" +
                "    FormException(1001, \"表单验证异常\");\n" +
                "\n" +
                "    /**\n" +
                "     * 状态码\n" +
                "     */\n" +
                "    private int code;\n" +
                "    /**\n" +
                "     * 提示语\n" +
                "     */\n" +
                "    private String message;\n" +
                "\n" +
                "    MsgCode(int code, String message) {\n" +
                "        this.code = code;\n" +
                "        this.message = message;\n" +
                "    }\n" +
                "\n" +
                "    public int getCode() {\n" +
                "        return code;\n" +
                "    }\n" +
                "\n" +
                "    public String getMessage() {\n" +
                "        return message;\n" +
                "    }\n" +
                "}");

        return sb.toString();
    }

}
