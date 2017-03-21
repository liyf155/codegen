package com.lee.codegen.setting;

import com.lee.codegen.generator.impl.DatabaseGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 通过spring配置文件来控制要生成的功能
 *
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/19 下午4:39
 */
public class GenerateController {
    private static GenerateController instance = new GenerateController();
    private ApplicationContext context; // 上下文

    private GenerateController() {
        this.context = new ClassPathXmlApplicationContext("classpath:spring-codegen.xml");
    }

    public static GenerateController getInstance() {
        if (instance == null) {
            instance = new GenerateController();
        }
        return instance;
    }

    public DatabaseGenerator getDatabaseGenerator(String databaseType){
        return this.context.getBean(databaseType, DatabaseGenerator.class);
    }
}
