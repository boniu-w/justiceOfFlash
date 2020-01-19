package com.yx.conditional;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author wg
 * @Package com.yx.conditional
 * @date 2019/10/22 14:26
 * @Copyright
 */
public class LinuxConditional implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        // 获取 ioc 使用的 beanfactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

        // 获取 类加载器
        ClassLoader classLoader = context.getClassLoader();


        // 获取环境
        Environment environment = context.getEnvironment();
        // 获取 系统 名称
        String property = environment.getProperty("os.name");
        if (property.contains("Windows")) {
            return false;
        }

        // 获取 bean定义的 注册类
        BeanDefinitionRegistry registry = context.getRegistry();

        return false;
    }
}
