package com.yx.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;

import java.beans.PropertyVetoException;

/**
 * @author wg
 * @Package com.yx.config
 * @date 2019/10/28 10:01
 * @Copyright
 */
@EnableTransactionManagement
@ComponentScan(
  value = "com.yx",
  includeFilters = {
    @ComponentScan.Filter(
      type = FilterType.ANNOTATION,
      classes = {
        Service.class, Repository.class
      }
    )
  }
)
@PropertySource(value = {"classpath:/dbconfig.properties"})
@Configuration
public class TxConfig {

    @Value("${db.user}")
    private String user;

    @Value("${db.password}")
    private String password;

    @Value("${db.url}")
    private String jdbcUrl;

    @Value("${db.driver}")
    private String driverClass;

    // 数据源
    @Bean
    public DataSource getDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() throws PropertyVetoException {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        return jdbcTemplate;
    }


    // 注册事务 到 容器中
    @Bean
    public PlatformTransactionManager getPlatformTransactionManager() throws PropertyVetoException {

        DataSourceTransactionManager dataSourceTransactionManager =
          new DataSourceTransactionManager(getDataSource());
        return dataSourceTransactionManager;
    }
}
