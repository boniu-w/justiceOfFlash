import com.yx.config.ProfileConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Set;

/**
 * @author wg
 * @Package PACKAGE_NAME
 * @date 2019/10/12 14:43
 * @Copyright
 */
public class TestDataSource {

    /**
     * 激活 环境 的方式:
     * 1. 在 虚拟机 参数 位置 设置 -Dspring.profiles.active=dev
     * 2.  见test2
     *
     */

    @Test
    public void test(){

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ProfileConfig.class);

        Map<String, DataSource> beansOfType = applicationContext.getBeansOfType(DataSource.class);
        Set<Map.Entry<String, DataSource>> entries = beansOfType.entrySet();
        for (Map.Entry<String, DataSource> entry : entries) {
            System.out.println(entry.getKey()+"\t\t"+entry.getValue());
        }


        String[] beanNamesForType = applicationContext.getBeanNamesForType(DataSource.class);
        for (String s : beanNamesForType) {
            System.out.println("容器的beans名字-- "+s);
        }

        applicationContext.close();
    }

    @Test
    public void test2(){

        // 1.无参构造器 创建 AnnotationConfigApplicationContext
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 2. 设置 需要激活 的环境 允许 test dev 加载
        applicationContext.getEnvironment().setActiveProfiles("test","dev");

        // 3. 开始 注册 配置类
        applicationContext.register(ProfileConfig.class);

        // 4. 刷新容器
        applicationContext.refresh();

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

        String[] beanNamesForType = applicationContext.getBeanNamesForType(DataSource.class);
        for (String s : beanNamesForType) {
            System.out.println("容器的beans名字-- "+s);
        }

        applicationContext.close();
    }

}
