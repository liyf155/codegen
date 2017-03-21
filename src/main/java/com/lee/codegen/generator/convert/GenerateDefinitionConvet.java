package com.lee.codegen.generator.convert;

import com.lee.codegen.definition.TableDefinition;
import com.lee.codegen.generator.def.TableGenerateDefinition;

/**
 * 数据定义对象到生成定义转换器接口
 *
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/20 下午4:41
 */
public interface GenerateDefinitionConvet {
    /**
     * 数据定义转换为生成定义
     *
     * @param tableDefinition
     * @param tableGenerateDefinition
     */
    void convert(TableDefinition tableDefinition, TableGenerateDefinition tableGenerateDefinition);
}
