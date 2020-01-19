package com.yx.config;

import com.yx.bean.Machine;
import com.yx.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wg
 * @Package com.yx.config
 * @date 2019/10/22 17:02
 * @Copyright
 */

@Configuration
public class BeanLifeCycle {

    @Bean(name = "machine", initMethod = "init", destroyMethod = "destory")
    public Machine getMachine() {

        return new Machine();
    }
}
