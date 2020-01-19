package com.yx.config;

import com.yx.aop.LogAspect;
import com.yx.aop.MathCaculation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author wg
 * @Package com.yx.config
 * @date 2019/10/25 10:52
 * @Copyright
 */
@EnableAspectJAutoProxy
@Configuration
public class AopConfig {

    @Bean(value={"MathCaculation"})
    public MathCaculation getMathCaculation(){
        return new MathCaculation();
    }

    @Bean
    public LogAspect getLogAspect(){
        return new LogAspect();
    }
}
