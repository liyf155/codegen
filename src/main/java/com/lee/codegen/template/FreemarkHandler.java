package com.lee.codegen.template;

import com.lee.codegen.setting.GenerateSetting;
import freemarker.core.ParseException;
import freemarker.template.*;

import java.io.*;

/**
 * 代码模板文件处理类
 *
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/19 下午4:11
 */
public class FreemarkHandler {
    private static FreemarkHandler instance = new FreemarkHandler();

    private Configuration config;
    private String charset = "UTF-8";

    private FreemarkHandler() {
    }

    public static FreemarkHandler getInstance() {
        if (instance == null) {
            instance = new FreemarkHandler();
        }
        return instance;
    }

    /**
     * 数据初始化
     */
    public void init() {
        config = new Configuration(Configuration.VERSION_2_3_23);
        GenerateSetting setting = GenerateSetting.getInstance();

        try {
            // 将Freemark模板初始化
            config.setDirectoryForTemplateLoading(new File(setting.getFreemarkTemplateDir()));
            if (setting.getFreemarkCharset() != null) {
                this.charset = setting.getFreemarkCharset();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 设置包装器,并将对象包装为数据模型
        config.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_23));
    }

    /**
     * 代码模板处理方法
     *
     * @param templeateName
     * @param outputFilePath
     * @param root
     */
    public void handle(String templeateName, String outputFilePath, Object root) {
        Writer out = null;
        try {
            // 获取模板，并设置编码方式
            Template template = config.getTemplate(templeateName, this.charset);
            // 合并数据模型与模板
            FileOutputStream fos = new FileOutputStream(outputFilePath);
            out = new OutputStreamWriter(fos, this.charset);
            template.process(root, out);
            out.flush();
        } catch (MalformedTemplateNameException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (TemplateNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
