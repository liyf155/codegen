package com.lee.codegen.definition;

/**
 * 数据定义基类
 *
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/17 下午5:25
 */
public class BaseDefinition {
    /**
     * ID
     */
    private String id;
    /**
     * 编码
     */
    private String code;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 名称

     */
    private String name;

}
