import com.yx.config.ProfileConfig;
import com.yx.config.TxConfig;
import com.yx.service.TxDemoService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wg
 * @Package PACKAGE_NAME
 * @date 2019/10/28 11:20
 * @Copyright
 */
public class TxDemoTest {

    @Test
    public void test(){

        AnnotationConfigApplicationContext applicationContext =
          new AnnotationConfigApplicationContext(TxConfig.class);

        TxDemoService txDemoService = applicationContext.getBean(TxDemoService.class);

        txDemoService.update();

        applicationContext.close();

    }
}
