package com.yx.config;

import com.yx.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author wg
 * @Package com.yx.config
 * @date 2019/10/18 14:58
 * @Copyright
 */

/**
 * propertySource 导入外部的配置文件 person.properties
 */
@PropertySource(value = {"classpath:/person.properties"})
@Configuration
public class PersonConfig {

    @Bean
    public Person getPerson(){

        return new Person();
    }
}
