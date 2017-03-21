package com.lee.codegen.generator.plugin.impl;

import com.google.common.collect.Lists;
import com.lee.codegen.definition.ColumnDefinition;
import com.lee.codegen.definition.TableDefinition;
import com.lee.codegen.generator.convert.FieldConvert;
import com.lee.codegen.generator.def.FieldGenerateDefinition;
import com.lee.codegen.generator.def.TableGenerateDefinition;
import com.lee.codegen.generator.plugin.GenerateDefinitionConvertPlugin;

import java.util.List;
import java.util.Map;

/**
 * 类型名称	显示长度	数据库类型		JAVA类型		JDBC类型索引
 * BLOB 	L+N 	BLOB 	java.lang.byte[] 	-4
 * BIT 	1 	BIT 	java.lang.Boolean 	-7
 * BIGINT 	20 	BIGINT UNSIGNED 	java.math.BigInteger 	-5
 * FLOAT 	4+8 	FLOAT 	java.lang.Float 	7
 * DOUBLE 	22 	DOUBLE 	java.lang.Double 	8
 * DECIMAL 	11 	DECIMAL 	java.math.BigDecimal 	3
 * BOOLEAN 	1 	同TINYINT
 * ID 	11 	PK (INTEGER UNSIGNED) 	java.lang.Long 	4
 * INTEGER 	4 	INTEGER UNSIGNED 	java.lang.Long 	4
 *
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/20 下午4:52
 */
public class MySqlGenDefConvertPlugin implements GenerateDefinitionConvertPlugin {
    private List<FieldConvert> fieldConverts = Lists.newArrayList();

    @Override
    public void convert(TableDefinition tableDefinition, TableGenerateDefinition tableGenerateDefinition) {
        List<ColumnDefinition> columnDefinitions = tableDefinition.getPrimaryKeys();

        FieldGenerateDefinition fieldGenerateDefinition = null;
        FieldConvert convert = null;
        ColumnDefinition columnDefinition = null;
        if (columnDefinitions != null && columnDefinitions.size() > 0) {
            int length = columnDefinitions.size() - 1;
            do {
                columnDefinition = columnDefinitions.get(length);
                fieldGenerateDefinition = new FieldGenerateDefinition(columnDefinition);
                for (int i = 0; i < fieldConverts.size(); i++) {
                    convert = fieldConverts.get(i);
                    if (convert.isEnable(columnDefinition)) {
                        convert.convert(columnDefinition, fieldGenerateDefinition);
                    }
                }
                tableGenerateDefinition.addIdentifyField(fieldGenerateDefinition);
                length--;
            } while (length > 0);
        }

		Map<String, ColumnDefinition> columnMap = tableDefinition.getColumns();
		if (columnMap != null && columnMap.size() > 0) {
			for (Map.Entry<String, ColumnDefinition> entry : columnMap.entrySet()) {
                columnDefinition = entry.getValue();
				fieldGenerateDefinition = new FieldGenerateDefinition(entry.getValue());
				for (int i = 0; i < fieldConverts.size(); i++) {
					convert = fieldConverts.get(i);
					if (convert.isEnable(columnDefinition)) {
						convert.convert(columnDefinition, fieldGenerateDefinition);
					}
				}

				tableGenerateDefinition.addIdentifyField(fieldGenerateDefinition);
			}
		}

    }

    public List<FieldConvert> getFieldConverts() {
        return fieldConverts;
    }

    public void setFieldConverts(List<FieldConvert> fieldConverts) {
        this.fieldConverts = fieldConverts;
    }
}
