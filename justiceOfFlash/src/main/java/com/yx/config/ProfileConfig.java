package com.yx.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.yx.bean.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 *
 * profile 环境标识 指定 组件 在哪个环境中 才能被 注册到容器中 不指定 都能被注册
 *
 * @author wg
 * @Package com.yx.config
 * @date 2019/10/24 14:09
 * @Copyright
 */
@PropertySource(value = {"classpath:/dbconfig.properties"})
@Configuration
public class ProfileConfig implements EmbeddedValueResolverAware {

    @Value("${db.user}")
    private String user;

    @Value("${db.password}")
    private String password;

    private StringValueResolver valueResolver;

    private String driverClass;


    @Profile("test")
    @Bean("person")
    public Person getPerson(){

        return new Person();
    }

    @Profile("test")
    @Bean("testDataSource")
    public DataSource getDataSourceTest(@Value("${db.password}") String password) throws PropertyVetoException {

        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(password);
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/wg");

        comboPooledDataSource.setDriverClass(driverClass);

        return null;
    }

    @Profile("dev")
    @Bean("devDataSource")
    public DataSource getDataSourceDev() throws PropertyVetoException {

        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(password);
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/jpa");
        comboPooledDataSource.setDriverClass(driverClass);

        return null;
    }

    @Profile("prod")
    @Bean("producDataSource")
    public DataSource getDataSourceProduc() throws PropertyVetoException {

        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(password);
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/jeecg-boot");
        comboPooledDataSource.setDriverClass(driverClass);

        return null;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.valueResolver=resolver;

        this.driverClass=valueResolver.resolveStringValue("${db.driver}");
    }
}
