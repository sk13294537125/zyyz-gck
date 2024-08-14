package com.gc.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.gc.constant.WolfLyConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


/**
 * MybatisPlus生成器
 */
@Slf4j
public class MybatisPlusGenerator {

    /**
     * 执行Mybatis生成器
     */
    public static void main(String[] args) {
        String[] tableNameArray = new String[]{"tc_eticket_black_list"};
        String projectPath = System.getProperty("user.dir");
        for (ProjectModuleEnum projectModule : ProjectModuleEnum.values()) {
            StringBuilder pathBuilder = new StringBuilder(projectPath);
            pathBuilder.append("/").append(WolfLyConstant.PROJECT_NAME).append("-").append(projectModule.getName());
            // 代码生成器
            // 数据源配置
            FastAutoGenerator shikai = FastAutoGenerator.create(createDataSourceConfig())
                    .globalConfig(builder -> builder.outputDir(pathBuilder + "/src/main/java")
                            .author("shikai")
                            .disableOpenDir()
                            .dateType(DateType.ONLY_DATE)
                            .commentDate("yyyy-MM-dd"))
                    .packageConfig(builder -> builder
                            .parent(WolfLyConstant.PARENT)
                            .service(WolfLyConstant.SERVICE)
                            .serviceImpl(WolfLyConstant.SERVICE_IMPL)
                            .mapper(WolfLyConstant.MAPPER)
                            .entity(WolfLyConstant.ENTITY)
                            .xml(WolfLyConstant.MAPPING))
                    .strategyConfig(builder -> {
                        builder.addInclude(tableNameArray)
                                .entityBuilder()
                                .fileOverride()
                                .enableLombok()
                                .enableTableFieldAnnotation() // 启用字段注解
                                .mapperBuilder()
                                .enableMapperAnnotation();
                    })
                    .templateConfig(builder -> {
                        builder.controller("")
                                .service("")
                                .serviceImpl("")
                                .mapper("")
                                .xml("");
                    })
                    .templateEngine(new VelocityTemplateEngine());

            shikai.execute();
        }
    }

    /**
     * 数据源配置
     *
     * @return 数据源配置
     */
    private static DataSourceConfig.Builder createDataSourceConfig() {
        String url = "jdbc:mysql://127.0.0.1:3306/tc_db?characterEncoding=utf-8&rewriteBatchedStatements=true";
        String userName = "root";
        String password = "root";
        return new DataSourceConfig.Builder(url, userName, password);
    }

    /**
     * 项目模块枚举
     */
    @Getter
    @AllArgsConstructor
    public enum ProjectModuleEnum {
        APP("app"),
        API("api");
        /**
         * 项目模块名称
         */
        private final String name;
    }
}
