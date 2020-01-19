package com.yx.config;

import com.yx.bean.Person;
import com.yx.controller.ImpController;
import com.yx.controller.ImpController2;
import org.springframework.context.annotation.*;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @author wg
 * @Package com.yx.config
 * @date 2019/10/12 14:51
 * @Copyright
 */
@Configuration
@ComponentScan(value = "com.yx", excludeFilters = {
  @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Service.class, Repository.class})})
public class BeanConfig {


    //    @Scope("singleton")
    // @Lazy  // 懒加载
    @Bean("zhangsan")
    public Person person() {
        System.out.println("创建person bean---");
        return new Person("zhangsan", 99);
    }


    @Bean
    public ImpController invokeImpController() {

        ImpController impController = new ImpController();
        return impController;
    }

    @Bean
    public ImpController2 invokeImpController2() {

        ImpController2 impController2 = new ImpController2();
        return impController2;
    }
}
