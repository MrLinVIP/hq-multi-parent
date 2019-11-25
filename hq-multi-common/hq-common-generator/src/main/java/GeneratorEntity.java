import java.sql.*;
import java.util.List;

/**
 * @author: linliangkun
 * @date: 2019/11/25 0025
 * @description:生成实体类
 */
public class GeneratorEntity {
    /**
     * 生成实体类
     *
     * @param tableInfoList
     * @throws Exception
     */
    public static void generatorEntitys(List<TableInfo> tableInfoList, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSetMetaData rsmd = null;
        for (int i = 0; i < tableInfoList.size(); i++) {
            TableInfo tableInfo = tableInfoList.get(i);
            String tableName = tableInfo.getTableName();
            System.out.println("开始生成表bean：" + tableName);
            String sql = "select * from " + tableName;
            pstmt = conn.prepareStatement(sql);
            rsmd = pstmt.getMetaData();
            int size = rsmd.getColumnCount();
            // 共有多少列
            String[] colnames = new String[size];
            String[] colTypes = new String[size];
            int[] colSizes = new int[size];
            for (int j = 0; j < rsmd.getColumnCount(); j++) {
                colnames[j] = rsmd.getColumnName(j + 1);
                colTypes[j] = rsmd.getColumnTypeName(j + 1);
                if (colTypes[j].equalsIgnoreCase("datetime") || colTypes[j].equalsIgnoreCase("time")) {
                    tableInfo.setF_util(true);
                }
                if (colTypes[j].equalsIgnoreCase("decimal")) {
                    tableInfo.setHasDecimal(true);
                }
                if ("id".equals(colnames[j]) || "id_".equals(colnames[j])) {
                    if (!colTypes[j].equalsIgnoreCase("char")) {
                        tableInfo.setF_auto(true);
                    }
                }
                colSizes[j] = rsmd.getColumnDisplaySize(j + 1);
            }
            tableInfo.setColNames(colnames);
            tableInfo.setColTypes(colTypes);
            tableInfo.setColSizes(colSizes);
            FileDateUtil.writeBeanFile(GeneratorUtil.initTableName(tableName), parseToBean(conn, tableInfo), GeneratorConstant.ENTITY_PACKAGE_NAME, true);
        }
        conn.close();
    }

    /**
     * 解析成实体bean对象字符串
     *
     * @param conn      数据库连接
     * @param tableInfo 表信息
     * @return 返回代码
     * @throws SQLException SQL异常
     */
    private static String parseToBean(Connection conn, TableInfo tableInfo) throws SQLException {
        StringBuffer sb = new StringBuffer();
        sb.append("import io.swagger.annotations.ApiModel;\n" +
                "import io.swagger.annotations.ApiModelProperty;\n" +
                "import lombok.Data;\n" +
                "import lombok.experimental.Accessors;\n\n" +
                "import javax.persistence.*;\n" +
                "import java.io.Serializable;\n" +
                "\n");
        if (tableInfo.isHasDecimal()) {
            sb.append("import java.math.BigDecimal;\r\n");
        }
        if (tableInfo.isF_util()) {
            sb.append("import java.util.Date;\r\n");
        }
        sb.append("\n/**\r\n" + " * " + tableInfo.getRemarks() + "\r\n");
        sb.append(" *\r\n");
        sb.append(" * @author Administrator\r\n");
        sb.append(" * @date " + FileDateUtil.dateFormat() + "\r\n" + " */\r\n");

        sb.append("@ApiModel(value = \"" + tableInfo.getRemarks() + "\")");
        sb.append("\n");
        sb.append("@Data");
        sb.append("\n");
        sb.append("@Accessors(chain = true)");
        sb.append("\n");
        sb.append("@Table(name = \"" + tableInfo.getTableName() + "\")");
        sb.append("\npublic class " + GeneratorUtil.initTableName(tableInfo.getTableName()));
        sb.append(" implements Serializable {");
        sb.append("\n\n" + "    private static final long serialVersionUID = 1L;" + "\n\n");
        /**
         * 生成实体属性
         */
        processAllAttrs(sb, conn, tableInfo);
        sb.append("}\r\n");
        return sb.toString();

    }

    /**
     * 解析输出属性
     *
     * @return
     * @throws SQLException
     */
    private static void processAllAttrs(StringBuffer sb, Connection conn, TableInfo tableInfo) throws SQLException {
        ResultSet rs = conn.getMetaData().getColumns(null, null, tableInfo.getTableName(), "%");
        while (rs.next()) {
            String colName = rs.getString("COLUMN_NAME");
            String remarks = rs.getString("REMARKS");
            if (remarks == null || remarks.equals("")) {
                remarks = colName;
            }
            String dbType = rs.getString("TYPE_NAME");

            System.out.println("COLUMN_NAME:" + colName + ",REMARKS:" + remarks + ",DBTYPE:" + dbType);
            sb.append("    /**\n" +
                    "     * " + remarks + "\n" +
                    "     */\n");
            if ("id".equals(colName) || "id_".equals(colName)) {
                sb.append("    @Id\n");
                if (!dbType.equalsIgnoreCase("char")) {
                    sb.append("    @GeneratedValue(strategy = GenerationType.IDENTITY)\n");
                }
            } else {
                sb.append("    @Column(name = \"" + colName + "\")\n");
            }
            sb.append("    @ApiModelProperty(value = \"" + remarks + "\", name = \"" + FileDateUtil.initcap(colName, false) + "\")\n");
            sb.append("    private " + sqlType2JavaType(dbType) + " " + FileDateUtil.initcap(colName, false) + ";\n\n");
        }
    }

    /**
     * SQL类型转为Java类型
     *
     * @param sqlType
     * @return
     */
    private static String sqlType2JavaType(String sqlType) {
        if (sqlType.equalsIgnoreCase("bit")) {
            return "Boolean";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "Byte";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "Short";
        } else if (sqlType.equalsIgnoreCase("int")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "Long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "Float";
        } else if (sqlType.equalsIgnoreCase("decimal")){
            return "BigDecimal";
        } else if (sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("double")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("money") || sqlType.equalsIgnoreCase("smallmoney")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
                || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
                || sqlType.equalsIgnoreCase("longtext")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("datetime") || sqlType.equalsIgnoreCase("date") || sqlType.equalsIgnoreCase("time")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("image")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("text")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("mediumtext")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("int unsigned")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("enum")) {
            return "String";
        } else {
            System.out.println("其他类型=====>sqlType====>" + sqlType + "其他类型===>");
            return "Integer";
        }
    }

    /**
     * 生成所有的GET SET方法
     *
     * @param sb
     */
    private void processAllMethods(StringBuffer sb, TableInfo tableInfo) {
        String[] colNames = tableInfo.getColNames();
        String[] colTypes = tableInfo.getColTypes();
        for (int i = 0; i < colNames.length; i++) {
            String clname = FileDateUtil.initcap(colNames[i], false);
            sb.append("    public void set" + FileDateUtil.initcap(colNames[i], true) + "(" + sqlType2JavaType(colTypes[i]) + " " + clname + ") {\r\n");
            sb.append("        this." + clname + " = " + clname + ";\r\n");
            sb.append("    }\r\n\n");
            sb.append("    public " + sqlType2JavaType(colTypes[i]) + " get" + FileDateUtil.initcap(colNames[i], true) + "() {\r\n");
            sb.append("        return " + clname + ";\r\n");
            sb.append("    }\r\n\n");
        }
    }
}
