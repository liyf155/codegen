package com.lee.codegen.generator;

import com.lee.codegen.definition.BaseDefinition;

/**
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/20 下午3:54
 */
public interface BaseGenerator<T extends BaseDefinition> {
    void generate(T t);
}
