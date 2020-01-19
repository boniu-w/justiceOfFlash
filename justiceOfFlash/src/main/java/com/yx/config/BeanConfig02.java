package com.yx.config;

import com.yx.bean.Animal;
import com.yx.bean.Machine;
import com.yx.bean.MyBeanFactory;
import com.yx.bean.Person;
import com.yx.conditional.LinuxConditional;
import com.yx.conditional.MyDefinitionBean;
import com.yx.conditional.WindowsConditional;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.*;

/**
 * @author wg
 * @Package com.yx.config
 * @date 2019/10/22 11:03
 * @Copyright
 */
@Configuration
@Import({Machine.class, Animal.class, MyDefinitionBean.class})
public class BeanConfig02 {

    /**
     *
     * conditional 根据一定的条件 触发
     *
     * @return
     */
    @Conditional({WindowsConditional.class})
    @Bean("bill")
    @Scope("singleton")
    public Person person(){
        Person bill = new Person("bill", 68);
        return bill;
    }

    @Conditional({LinuxConditional.class})
    @Bean("limei")
    public Person person02(){
        Person limei = new Person("limei", 30);
        return limei;
    }

    @Bean("myBeanFactory")
    public MyBeanFactory getBeanFactory(){

        return  new MyBeanFactory();
    }

}
