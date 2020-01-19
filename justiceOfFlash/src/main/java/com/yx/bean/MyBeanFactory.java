package com.yx.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author wg
 * @Package com.yx.bean
 * @date 2019/10/22 16:30
 * @Copyright
 */
public class MyBeanFactory implements FactoryBean<Animal> {
    @Override
    public Animal getObject() throws Exception {
        return new Animal();
    }

    @Override
    public Class<?> getObjectType() {
        return Animal.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
