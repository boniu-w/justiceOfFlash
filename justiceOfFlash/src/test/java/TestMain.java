import com.yx.bean.Person;
import com.yx.conditional.MyDefinitionBean;
import com.yx.config.BeanConfig;
import com.yx.config.BeanConfig02;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;

import java.util.Map;
import java.util.Set;

/**
 * @author wg
 * @Package PACKAGE_NAME
 * @date 2019/10/12 14:43
 * @Copyright
 */
public class TestMain {


    /**
     * xml 方式
     *
     * @param args
     */
    public static void main(String[] args) {

        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("iocTest.xml");
        Person person = (Person) classPathXmlApplicationContext.getBean("person");

        Integer age = person.getAge();
        String name = person.getName();

        System.out.println(age + "-----" + name);


    }


    /**
     * 配置类 方式
     */
    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);


        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String bean : beanDefinitionNames) {

            System.out.println(bean);
        }


    }


    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig02.class);
        String[] beanNamesForType = applicationContext.getBeanNamesForType(BeanConfig02.class);

        String[] persons = applicationContext.getBeanNamesForType(Person.class);

        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();

        System.out.println(propertySources);

        Map<String, Object> properties = environment.getSystemProperties();
        String property = environment.getProperty("os.name"); // 获取环境变量

        System.out.println("获取环境变量---" + property);


        Set<Map.Entry<String, Object>> entrySet = properties.entrySet();
        for (Map.Entry<String, Object> stringObjectEntry : entrySet) {
            System.out.print(stringObjectEntry.getKey() + "----");
            System.out.println(stringObjectEntry.getValue());

        }


        for (String s : beanNamesForType) {
            System.out.println(s);
        }

        for (String person : persons) {
            System.out.println(person);
        }


    }


    @Test
    public void getBeans() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig02.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("bean-- " + beanDefinitionName);
        }

    }


    @Test
    public void getBeanFactory() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig02.class);

        // 加 & 前缀 得到
        Object bean = applicationContext.getBean("&myBeanFactory");
        System.out.println("bean 的类型:--" + bean.getClass());

        Object myBeanFactory = applicationContext.getBean("myBeanFactory");
        System.out.println("myBeanFactory 的 类型:-- "+myBeanFactory.getClass());

    }
}
