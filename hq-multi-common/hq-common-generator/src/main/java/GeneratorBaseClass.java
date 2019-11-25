/**
 * @author chenzeke
 * @date 2018/12/2 20:25
 * 生成基础类
 */
public class GeneratorBaseClass {
    /**
     * 是否覆盖重新生成基础类文件
     */
    private static boolean cover = true;

    public static void main(String[] args) {
        generatorBaseClass();
    }

    public static void generatorBaseClass() {
        GeneratorBaseMapper();
        GeneratorBaseService();
        GeneratorAbstractBaseService();
    }

    /**
     * 生成通用数据层接口，其他mapper请继承该mapper
     */
    public static void GeneratorBaseMapper() {
        String className = "BaseMapper";
        String content = getBaseMapperContent();
        String packageName = GeneratorConstant.BASE_PACKAGE_NAME;
        FileDateUtil.writeBeanFile(className, content, packageName, cover);
    }

    /**
     * @return 返回BaseMapper代码
     */
    private static String getBaseMapperContent() {
        StringBuilder sb = new StringBuilder();
        sb.append("import tk.mybatis.mapper.common.Mapper;\n" +
                "import tk.mybatis.mapper.common.MySqlMapper;\n" +
                "import tk.mybatis.mapper.common.special.InsertListMapper;\n" +
                "\n");
        sb.append(FileDateUtil.getAnnotation("通用Mapper接口,其他接口继承该接口即可"));
        sb.append("public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T>, InsertListMapper<T> {\n" +
                "}");
        return sb.toString();
    }

    /**
     * 基础方法接口类
     */
    public static void GeneratorBaseService() {
        String className = "BaseService";
        String content = getBaseServiceContent();
        String packageName = GeneratorConstant.BASE_PACKAGE_NAME;
        FileDateUtil.writeBeanFile(className, content, packageName, cover);
    }

    /**
     * @return 返回BaseService代码
     */
    private static String getBaseServiceContent() {
        StringBuilder sb = new StringBuilder();
        sb.append("import org.apache.ibatis.session.RowBounds;");
        sb.append("\n");
        sb.append("\n");
        sb.append("import java.util.List;");
        sb.append("\n");
        sb.append("\n");
        sb.append(FileDateUtil.getAnnotation("通用操作数据库方法接口"));
        sb.append("public interface BaseService<T> {");
        sb.append("\n");
        sb.append("    /**\n" +
                "     * 保存一个实体，null的属性也会保存，不会使用数据库默认值\n" +
                "     *\n" +
                "     * @param t record\n" +
                "     * @return 返回操作结果\n" +
                "     */\n" +
                "    int insert(T t);\n" +
                "\n" +
                "    /**\n" +
                "     * 保存一个实体，null的属性不会保存，会使用数据库默认值\n" +
                "     *\n" +
                "     * @param t record\n" +
                "     * @return 返回操作结果\n" +
                "     */\n" +
                "    int insertSelective(T t);\n" +
                "\n" +
                "    /**\n" +
                "     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含`id`属性并且必须为自增列\n" +
                "     *\n" +
                "     * @param t record\n" +
                "     * @return 返回操作结果\n" +
                "     */\n" +
                "    int insertList(List<T> t);\n" +
                "\n" +
                "    /**\n" +
                "     * 根据实体属性作为条件进行删除，查询条件使用等号\n" +
                "     *\n" +
                "     * @param t record\n" +
                "     * @return 返回操作结果\n" +
                "     */\n" +
                "    int delete(T t);\n" +
                "\n" +
                "    /**\n" +
                "     * 根据Example条件删除数据\n" +
                "     *\n" +
                "     * @param o example\n" +
                "     * @return 返回操作结果\n" +
                "     */\n" +
                "    int deleteByExample(Object o);\n" +
                "\n" +
                "    /**\n" +
                "     * 根据主键字段进行删除，方法参数必须包含完整的主键属性\n" +
                "     *\n" +
                "     * @param o 主键key\n" +
                "     * @return 返回操作结果\n" +
                "     */\n" +
                "    int deleteByPrimaryKey(Object o);\n" +
                "\n" +
                "    /**\n" +
                "     * 根据主键更新实体全部字段，null值会被更新\n" +
                "     *\n" +
                "     * @param t record\n" +
                "     * @return 返回操作结果\n" +
                "     */\n" +
                "    int updateByPrimaryKey(T t);\n" +
                "\n" +
                "    /**\n" +
                "     * 根据Example条件更新实体`record`包含的全部属性，null值会被更新\n" +
                "     *\n" +
                "     * @param t record\n" +
                "     * @param o example\n" +
                "     * @return 返回操作结果\n" +
                "     */\n" +
                "    int updateByExample(T t, Object o);\n" +
                "\n" +
                "    /**\n" +
                "     * 根据Example条件更新实体`record`包含的不是null的属性值\n" +
                "     *\n" +
                "     * @param t record\n" +
                "     * @param o example\n" +
                "     * @return 返回操作结果\n" +
                "     */\n" +
                "    int updateByExampleSelective(T t, Object o);\n" +
                "\n" +
                "    /**\n" +
                "     * 根据主键更新属性不为null的值\n" +
                "     *\n" +
                "     * @param t record\n" +
                "     * @return 返回操作结果\n" +
                "     */\n" +
                "    int updateByPrimaryKeySelective(T t);\n" +
                "\n" +
                "    /**\n" +
                "     * 根据实体中的属性值进行查询，查询条件使用等号\n" +
                "     *\n" +
                "     * @param t record\n" +
                "     * @return 返回操作结果\n" +
                "     */\n" +
                "    List<T> select(T t);\n" +
                "\n" +
                "    /**\n" +
                "     * 查询全部结果\n" +
                "     *\n" +
                "     * @return 返回列表数据\n" +
                "     */\n" +
                "    List<T> selectAll();\n" +
                "\n" +
                "    /**\n" +
                "     * 根据Example条件进行查询\n" +
                "     *\n" +
                "     * @param o example\n" +
                "     * @return\n" +
                "     */\n" +
                "    List<T> selectByExample(Object o);\n" +
                "\n" +
                "    /**\n" +
                "     * 根据example条件和RowBounds进行分页查询\n" +
                "     *\n" +
                "     * @param o         example\n" +
                "     * @param rowBounds rowBounds\n" +
                "     * @return 返回列表数据\n" +
                "     */\n" +
                "    List<T> selectByExampleAndRowBounds(Object o, RowBounds rowBounds);\n" +
                "\n" +
                "    /**\n" +
                "     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号\n" +
                "     *\n" +
                "     * @param o key\n" +
                "     * @return 返回实体对象\n" +
                "     */\n" +
                "    T selectByPrimaryKey(Object o);\n" +
                "\n" +
                "    /**\n" +
                "     * 根据实体属性和RowBounds进行分页查询\n" +
                "     *\n" +
                "     * @param t         record\n" +
                "     * @param rowBounds rowBounds\n" +
                "     * @return 返回列表数据\n" +
                "     */\n" +
                "    List<T> selectByRowBounds(T t, RowBounds rowBounds);\n" +
                "\n" +
                "    /**\n" +
                "     * 根据实体中的属性查询总数，查询条件使用等号\n" +
                "     *\n" +
                "     * @param t record\n" +
                "     * @return 返回查询总数\n" +
                "     */\n" +
                "    int selectCount(T t);\n" +
                "\n" +
                "    /**\n" +
                "     * 根据Example条件进行查询总数\n" +
                "     *\n" +
                "     * @param o example\n" +
                "     * @return 返回查询总数\n" +
                "     */\n" +
                "    int selectCountByExample(Object o);\n" +
                "\n" +
                "    /**\n" +
                "     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号\n" +
                "     *\n" +
                "     * @param t record\n" +
                "     * @return 返回实体对象\n" +
                "     */\n" +
                "    T selectOne(T t);\n" +
                "\n" +
                "    /**\n" +
                "     * 根据Example条件进行查询\n" +
                "     *\n" +
                "     * @param o example\n" +
                "     * @return 返回实体对象\n" +
                "     */\n" +
                "    T selectOneByExample(Object o);\n" +
                "\n" +
                "    /**\n" +
                "     * 根据主键字段查询总数，方法参数必须包含完整的主键属性，查询条件使用等号\n" +
                "     *\n" +
                "     * @param o key\n" +
                "     * @return 返回true/false\n" +
                "     */\n" +
                "    boolean existsWithPrimaryKey(Object o);\n" +
                "}");
        return sb.toString();
    }

    /**
     * 生成抽象类型的基础方法操作
     */
    public static void GeneratorAbstractBaseService() {
        String className = "AbstractBaseService";
        String content = getAbstractBaseServiceContent();
        String packageName = GeneratorConstant.BASE_PACKAGE_NAME;
        FileDateUtil.writeBeanFile(className, content, packageName, cover);
    }

    private static String getAbstractBaseServiceContent() {
        StringBuilder sb = new StringBuilder();
        sb.append("import org.apache.ibatis.session.RowBounds;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "\n" +
                "import java.io.Serializable;\n" +
                "import java.util.List;\n" +
                "\n");
        sb.append(FileDateUtil.getAnnotation("抽象类实现基础操作数据库接口方法"));
        sb.append("public abstract class AbstractBaseService<M extends BaseMapper<T>, T extends Serializable> implements BaseService<T> {\n" +
                "\n" +
                "    @Autowired\n" +
                "    protected M mapper;\n" +
                "\n" +
                "    @Override\n" +
                "    public int insert(T t) {\n" +
                "        return this.mapper.insert(t);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public int insertSelective(T t) {\n" +
                "        return this.mapper.insertSelective(t);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public int insertList(List<T> t) {\n" +
                "        return this.mapper.insertList(t);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public int delete(T t) {\n" +
                "        return mapper.delete(t);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public int deleteByExample(Object o) {\n" +
                "        return this.mapper.deleteByExample(o);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public int deleteByPrimaryKey(Object o) {\n" +
                "        return this.mapper.deleteByPrimaryKey(o);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public int updateByPrimaryKey(T t) {\n" +
                "        return this.mapper.updateByPrimaryKey(t);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public int updateByExample(T t, Object o) {\n" +
                "        return this.mapper.updateByExample(t, o);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public int updateByExampleSelective(T t, Object o) {\n" +
                "        return this.mapper.updateByExampleSelective(t, o);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public int updateByPrimaryKeySelective(T t) {\n" +
                "        return this.mapper.updateByPrimaryKeySelective(t);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public List<T> select(T t) {\n" +
                "        return this.mapper.select(t);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public List<T> selectAll() {\n" +
                "        return this.mapper.selectAll();\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public List<T> selectByExample(Object o) {\n" +
                "        return this.mapper.selectByExample(o);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public List<T> selectByExampleAndRowBounds(Object o, RowBounds rowBounds) {\n" +
                "        return this.mapper.selectByExampleAndRowBounds(o, rowBounds);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public T selectByPrimaryKey(Object o) {\n" +
                "        return this.mapper.selectByPrimaryKey(o);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public List<T> selectByRowBounds(T t, RowBounds rowBounds) {\n" +
                "        return this.mapper.selectByRowBounds(t, rowBounds);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public int selectCount(T t) {\n" +
                "        return this.mapper.selectCount(t);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public int selectCountByExample(Object o) {\n" +
                "        return this.mapper.selectCountByExample(o);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public T selectOne(T t) {\n" +
                "        return this.mapper.selectOne(t);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public T selectOneByExample(Object o) {\n" +
                "        return this.mapper.selectOneByExample(o);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public boolean existsWithPrimaryKey(Object o) {\n" +
                "        return this.mapper.existsWithPrimaryKey(o);\n" +
                "    }\n" +
                "}\n");
        return sb.toString();
    }
}
