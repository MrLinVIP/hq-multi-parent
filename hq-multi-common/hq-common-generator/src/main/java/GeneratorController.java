import java.util.List;

/**
 * 生成Controller
 *
 * @author chenzeke
 * @date 2018/11/22 18:27
 */
public class GeneratorController {

    /**
     * 是否覆盖Controller,Controller一般只生成一次，因为有可能增加业务逻辑
     */
    private static boolean cover = false;

    public static void generatorControllers(List<TableInfo> tableInfoList) {
        for (int i = 0; i < tableInfoList.size(); i++) {
            TableInfo tableInfo = tableInfoList.get(i);
            //表名
            String tableName = tableInfo.getTableName();
            //表对应的实体名称
            String className = GeneratorUtil.initTableName(tableName);
            //设置请求路径
            String path = GeneratorUtil.initTableName(tableName);
            //获取表备注
            String remarks = tableInfo.getRemarks();
            String content = getControllerContent(className, path, remarks);
            FileDateUtil.writeBeanFile(className + "Controller", content, GeneratorConstant.CONTROLLER_PACKAGE_NAME, cover);
        }
    }

    private static String getControllerContent(String className, String path, String remarks) {
        StringBuilder sb = new StringBuilder();
        sb.append(importContent(className));
        sb.append("\n\n");
        sb.append(FileDateUtil.getAnnotation(null));
        sb.append("@RestController\n" +
                "@RequestMapping(\"/" + path.substring(0, 1).toLowerCase() + path.substring(1) + "\")\n");
        sb.append("@Slf4j\n");
        sb.append("@Api(tags = \"" + remarks + "\")\n");
        sb.append("public class " + className + "Controller {");

        String classname = path.substring(0, 1).toLowerCase() + path.substring(1);
        sb.append("\n" +
                "    @Autowired\n" +
                "    private " + className + "Service " + classname + "Service;\n" +
                "\n");
        sb.append(getTestCode(className, classname));
        sb.append(getBusinessCode(className, path));
        sb.append("\n\n}");
        return sb.toString();
    }

    public static String importContent(String className) {
        StringBuilder sb = new StringBuilder();
        sb.append("import com.github.pagehelper.Page;\n" +
                "import com.github.pagehelper.PageHelper;\n" +
                "import com.github.pagehelper.PageInfo;\n" +
                "import " + GeneratorConstant.UTIL_PACKAGE_NAME + ".R;\n" +
                "import " + GeneratorConstant.ENTITY_PACKAGE_NAME + "." + className + ";\n" +
                "import " + GeneratorConstant.SERVICE_PACKAGE_NAME + "." + className + "Service;\n" +
                "import io.swagger.annotations.Api;\n" +
                "import io.swagger.annotations.ApiOperation;\n" +
                "import lombok.extern.slf4j.Slf4j;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.web.bind.annotation.*;\n" +
                "import tk.mybatis.mapper.entity.Example;");
        return sb.toString();
    }

    private static String getTestCode(String className, String name) {
        StringBuilder sb = new StringBuilder();
        sb.append("    @ApiOperation(value = \"保存数据\")\n" +
                "    @PostMapping(\"/save\")\n" +
                "    public R save(@RequestBody " + className + " " + name + ") {\n" +
                "        " + name + "Service.insertSelective(" + name + ");\n" +
                "        return R.ok();\n" +
                "    }\n" +
                "\n" +
                "    @ApiOperation(value = \"查看数据\")\n" +
                "    @GetMapping(\"/info/{id}\")\n" +
                "    public R info(@PathVariable(\"id\") Integer id) {\n" +
                "        " + className + " " + name + " = " + name + "Service.selectByPrimaryKey(id);\n" +
                "        return R.ok(" + name + ");\n" +
                "    }\n" +
                "\n" +
                "    @ApiOperation(value = \"修改数据\")\n" +
                "    @PutMapping(\"/update\")\n" +
                "    public R update(@RequestBody " + className + " " + name + ") {\n" +
                "        " + name + "Service.updateByPrimaryKeySelective(" + name + ");\n" +
                "        return R.ok();\n" +
                "    }\n" +
                "\n" +
                "    @ApiOperation(value = \"删除数据\")\n" +
                "    @DeleteMapping(\"/delete/{id}\")\n" +
                "    public R delete(@PathVariable(\"id\") Integer id) {\n" +
                "        " + name + "Service.deleteByPrimaryKey(id);\n" +
                "        return R.ok();\n" +
                "    }\n" +
                "\n" +
                "    @ApiOperation(value = \"查看列表数据\")\n" +
                "    @GetMapping(\"/list/{pageNum}/{pageSize}\")\n" +
                "    public R list(@PathVariable(\"pageNum\") Integer pageNum, @PathVariable(\"pageSize\") Integer pageSize) {\n" +
                "        Example example = new Example(" + className + ".class);\n" +
                "        //默认排序：id倒序排序\n" +
                "        example.orderBy(\"id\").desc();\n" +
                "        /**\n" +
                "         * 参数说明：\n" +
                "         * count=true,查询总数\n" +
                "         * reasonable=false,总数为10，查看第2页每页10条返回空，如果为true则页数>2每页10条都返回最后一页数据\n" +
                "         * pageSizeZero=true，当pageSize=0时候，返回全部数据，如果为false，返回空数据。\n" +
                "         * 建议使用：count=true，reasonable=false。pageSizeZero=false\n" +
                "         */\n" +
                "        Page<" + className + "> page = PageHelper.startPage(pageNum, pageSize, true, false, false);\n" +
                "        " + name + "Service.selectByExample(example);\n" +
                "        PageInfo<" + className + "> info = new PageInfo<>(page.getResult());\n" +
                "        return R.ok(info);\n" +
                "    }");
        return sb.toString();
    }

    private static String getBusinessCode(String className, String path) {
        StringBuilder sb = new StringBuilder();

        return sb.toString();
    }

}
