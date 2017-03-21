package com.lee.codegen.generator.plugin;

import com.lee.codegen.generator.def.TableGenerateDefinition;

/**
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/20 下午4:38
 */
public interface TableGeneratorPlugin {

    void generate(TableGenerateDefinition definition);
}
