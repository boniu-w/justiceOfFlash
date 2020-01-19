package com.example.springbootdemo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootDemoApplicationTests {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    void slf4jTest() {
        // 日志的级别
        logger.trace("这是 trace 日志```");
        logger.debug("这是 debug 日志```");
        logger.info("这是 info 日志```");
        logger.warn("这是 warn 日志```");
        logger.error("这是 error 日志```");
    }
}
