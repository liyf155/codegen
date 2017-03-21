package com.lee.codegen.definition;

/**
 * 字段属性定义
 *
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/17 下午5:28
 */
public class ColumnDefinition extends BaseDefinition {
    /**
     * 是否主键
     */
    private boolean identify;
    /**
     * 类型
     */
    private String dataType;
    /**
     * 注释
     */
    private String comment;
    /**
     * 是否可为空
     */
    private boolean nullable;

    public boolean isIdentify() {
        return identify;
    }

    public void setIdentify(boolean identify) {
        this.identify = identify;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }
}
