package com.yx.conditional;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author wg
 * @Package com.yx.conditional
 * @date 2019/10/22 16:12
 * @Copyright
 *
 * 手动 注册 bean
 *
 */
public class MyDefinitionBean implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        boolean b = registry.containsBeanDefinition("com.yx.bean.Machine");
        boolean b2 = registry.containsBeanDefinition("com.yx.bean.Animal");

        if(b && b2){
            RootBeanDefinition beanDefinition = new RootBeanDefinition("com.yx.bean.RainBow");
            registry.registerBeanDefinition("rainBow", beanDefinition);

        }


    }
}
