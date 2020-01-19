import com.yx.bean.Person;
import com.yx.config.BeanConfig;
import com.yx.config.BeanConfig02;
import com.yx.config.PersonConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wg
 * @Package PACKAGE_NAME
 * @date 2019/10/24 10:04
 * @Copyright
 */
public class TestPerson {


    @Test
    public void test(){

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(PersonConfig.class);

//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig02.class);

        Person person = applicationContext.getBean(Person.class);
        System.out.println(person);


    }
}
