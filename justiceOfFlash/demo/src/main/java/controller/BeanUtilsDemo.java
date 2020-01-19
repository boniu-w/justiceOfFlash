package controller;


import entity.Employee;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;


/**
 * @author wg
 * @Package PACKAGE_NAME
 * @date 2019/11/22 9:45
 * @Copyright
 */
public class BeanUtilsDemo {

    /**
     *  bean 和 map 互转
     * @param args
     */
    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setName("xiaomi");
        employee.setAge(19);
        try {
            // bean 转 map
            Map<String, String> map = BeanUtils.describe(employee);
            for (Map.Entry<String, String> entry : map.entrySet()) {

                System.out.println(entry.getKey()+"="+entry.getValue());
            }

            // map 转 bean
            map.put("name","lisi");
            BeanUtils.populate(employee,map);

            System.out.println(employee);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


    }
}
