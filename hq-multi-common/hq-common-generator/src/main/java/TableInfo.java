import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Admin
 */
@Data
@AllArgsConstructor
public class TableInfo implements Serializable {

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表备注
     */
    private String remarks;

    /**
     * 列名数组
     */
    private String[] colNames;

    /**
     * 列名类型数组
     */
    private String[] colTypes;

    /**
     * 列名大小数组
     */
    private int[] colSizes;

    /**
     * 是否需要导入包java.util.*
     */
    private boolean f_util = false;

    /**
     * 是否需要导入包java.math.BigDecimal
     */
    private boolean hasDecimal = false;

    /**
     * AUTO_INCREMENT
     */
    private boolean f_auto;

    public TableInfo(String tableName, String remarks) {
        super();
        this.tableName = tableName;
        this.remarks = remarks;
    }

}
