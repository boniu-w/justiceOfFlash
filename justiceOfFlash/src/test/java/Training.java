import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author wg
 * @Package PACKAGE_NAME
 * @date 2019/11/1 15:00
 * @Copyright
 */
public class Training {

    @Test
    public void test(){

        String s = UUID.randomUUID().toString();
        System.out.println(s);


    }

    @Test
    public void test2(){
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("name","xiaowu");
        hashMap.put("age","23");

        System.out.println(hashMap);

        JSON json = (JSON) JSON.toJSON(hashMap);
        System.out.println(json);

        String s = JSON.toJSONString(hashMap);
        System.out.println(s);


    }
}
