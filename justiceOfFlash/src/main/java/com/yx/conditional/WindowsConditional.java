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
 * @date 2019/10/22 14:25
 * @Copyright
 */
public class WindowsConditional implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        // 获取 ioc 使用的 beanfactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

        System.out.println("ioc 使用的 beanfactory--"+beanFactory);

        // 获取 类加载器
        ClassLoader classLoader = context.getClassLoader();
        System.out.println("类加载器--"+classLoader);

        // 获取 bean定义的 注册类
        BeanDefinitionRegistry registry = context.getRegistry();
        System.out.println("获取 bean定义的 注册类 ---"+registry);

        String[] beanDefinitionNames = registry.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("beanDefinitionNames..."+beanDefinitionName);
        }

        // 注册的bean 是否是 person
        boolean b = registry.containsBeanDefinition("person");
        System.out.println("registry.containsBeanDefinition(\"person\");---"+b);

        int beanDefinitionCount = registry.getBeanDefinitionCount();
        System.out.println("getBeanDefinitionCount ---"+beanDefinitionCount);

        // 获取环境
        Environment environment = context.getEnvironment();
        // 获取 系统 名称
        String property = environment.getProperty("os.name");
        if (property.contains("Windows")) {
            return true;

        }

        return false;
    }
}
