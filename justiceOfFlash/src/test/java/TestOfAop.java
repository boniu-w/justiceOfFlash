import com.yx.aop.LogAspect;
import com.yx.aop.MathCaculation;
import com.yx.config.AopConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wg
 * @Package PACKAGE_NAME
 * @date 2019/10/25 11:19
 * @Copyright
 */
public class TestOfAop {


    @Test
    public void test(){

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfig.class);

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("-------"+beanDefinitionName);
        }

        MathCaculation mathCaculation = applicationContext.getBean(MathCaculation.class);
        LogAspect logAspectBean = applicationContext.getBean(LogAspect.class);

        int i = mathCaculation.div(2,1 );


    }
}
