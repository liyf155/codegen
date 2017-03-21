package com.lee.codegen.definition;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 关联外键的定义
 *
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/17 下午5:31
 */
public class KeyDefinition extends BaseDefinition {
    /**
     * 关联的外键
     */
    private List<String> refColumnIds = Lists.newArrayList();

    public List<String> getRefColumnIds() {
        return refColumnIds;
    }

    public void setRefColumnIds(List<String> refColumnIds) {
        this.refColumnIds = refColumnIds;
    }

    public void addRefColumnIds(String columnId){
        this.refColumnIds.add(columnId);
    }
}
