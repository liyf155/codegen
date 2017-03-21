package com.lee.codegen.generator.convert;

import com.lee.codegen.definition.ColumnDefinition;
import com.lee.codegen.generator.def.FieldGenerateDefinition;

/**
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/19 下午1:53
 */
public interface FieldConvert {
    /**
     * 是否
     * @param columnDefinition
     * @return
     */
    boolean isEnable(ColumnDefinition columnDefinition);

    /**
     * 转换
     * @param columnDefinition
     * @param fieldGenerateDefinition
     */
	void convert(ColumnDefinition columnDefinition, FieldGenerateDefinition fieldGenerateDefinition);
}
