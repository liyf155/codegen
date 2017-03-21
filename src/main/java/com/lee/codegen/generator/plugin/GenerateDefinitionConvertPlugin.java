package com.lee.codegen.generator.plugin;

import com.lee.codegen.definition.TableDefinition;
import com.lee.codegen.generator.def.TableGenerateDefinition;

/**
 * 转换插件接口
 *
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/20 下午4:46
 */
public interface GenerateDefinitionConvertPlugin {
    /**
     *
     * @param tableDefinition
     * @param tableGenerateDefinition
     */
    void convert(TableDefinition tableDefinition, TableGenerateDefinition tableGenerateDefinition);
}
