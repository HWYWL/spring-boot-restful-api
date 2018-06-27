package com.yi.project.core;

/**
 * 公共常量
 * @author YI
 * @date 2018-6-27 16:07:43
 */
public final class ProjectConstant {
    /**
     * 项目基础包名称，可以自行修改
     */
    public static final String BASE_PACKAGE = "com.yi.project";

    /**
     * Mapper所在包
     */
    public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".dao";

    /**
     * Model所在包
     */
    public static final String MODEL_PACKAGE = BASE_PACKAGE + ".model";

    /**
     * Mapper插件基础接口的完全限定名
     */
    public static final String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE + ".core.GeneralMapper";
}
