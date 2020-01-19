import com.yx.bean.Person;
import com.yx.config.BeanConfig;
import com.yx.config.BeanLifeCycle;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wg
 * @Package PACKAGE_NAME
 * @date 2019/10/22 17:03
 * @Copyright
 */
public class TestLifeCycle {

    @Test
    public void test() {

        // 创建ioc 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanLifeCycle.class);

        Object person = applicationContext.getBean("machine");
        System.out.println(person.getClass());

//        applicationContext.close();


    }
}
