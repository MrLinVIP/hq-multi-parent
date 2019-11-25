import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: linliangkun
 * @date: 2019/11/25 0025
 * @description:文件日期工具类
 */
public class FileDateUtil {
    /**
     * 写入文件
     *
     * @param className   类名称
     * @param content     类内容
     * @param packageName 包名
     * @param cover       是否覆盖之前的生成
     */
    public static void writeBeanFile(String className, String content, String packageName, boolean cover) {
        String folder = GeneratorConstant.CODE_PATH + packageName.replace('.', '/') + "/";
        File file = new File(folder);
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = folder + className + ".java";
        try {
            File newDao = new File(fileName);
            if (newDao.exists() && !cover) {
                //已经存在并且不覆盖,返回
                return;
            }
            FileWriter fw = new FileWriter(newDao);
            fw.write("package " + packageName.replace("/", ".") + ";\r\n\n");
            fw.write(content);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入文件xml
     *
     * @param className   类名称
     * @param content     类内容
     * @param packageName 包名
     * @param cover       是否覆盖之前的生成
     */
    public static void writexmlFile(String className, String content, String packageName, boolean cover) {
        String folder = GeneratorConstant.CODE_PATH + packageName.replace('.', '/') + "/";
        File file = new File(folder);
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = folder + className + ".xml";
        try {
            File newDao = new File(fileName);
            if (newDao.exists() && !cover) {
                //已经存在并且不覆盖,返回
                return;
            }
            FileWriter fw = new FileWriter(newDao);
            fw.write(content);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 默认日期格式化
     *
     * @return 返回当前时间的字符串
     */
    public static String dateFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat(GeneratorConstant.DATE_TIME_PATTERN);
        return sdf.format(new Date());
    }

    /**
     * 类注释
     *
     * @param annotation
     * @return 返回类注释信息
     */
    public static String getAnnotation(String annotation) {
        StringBuilder sb = new StringBuilder();
        if (annotation == null || annotation.length() == 0) {
            sb.append("/**\n");
        } else {
            sb.append("/**\n" + " * " + annotation + "\n");
            sb.append(" *\n");
        }
        sb.append(" * @author root\r\n" +
                " * @date " + FileDateUtil.dateFormat() + "\r\n" +
                " */\r\n");
        return sb.toString();
    }

    /**
     * 把输入字符串的首字母改成大写
     *
     * @param colName
     * @param isFirstUper
     * @return
     */
    public static String initcap(String colName, boolean isFirstUper) {
        colName = subPrefix(colName);//先截取前缀
        StringBuilder sb = new StringBuilder();
        String arrays[] = colName.split("_");
        for (int i = 0; i < arrays.length; i++) {
            char[] ch = arrays[i].toCharArray();
            if (!isFirstUper) {
                if (i == 0) {
                    sb.append(ch);
                    continue;
                }
            }
            if (ch[0] >= 'a' && ch[0] <= 'z') {
                ch[0] = (char) (ch[0] - 32);
            }
            sb.append(ch);
        }
        return sb.toString();
    }

    /**
     * 截取table模块前缀
     *
     * @param colName
     */
    private static String subPrefix(String colName) {
        if (colName == null || colName.length() <= 4) {
            return colName;
        }
        int size = colName.length();
        if (colName.startsWith("mem_")) {
            return colName.substring(4, size);
        }
        if (colName.startsWith("bsm_")) {
            return colName.substring(4, size);
        }
        if (colName.startsWith("mer_")) {
            return colName.substring(4, size);
        }
        if (colName.startsWith("sys_")) {
            return colName.substring(4, size);
        }
        return colName;
    }
}
