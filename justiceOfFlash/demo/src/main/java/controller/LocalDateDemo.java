package controller;

import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author wg
 * @Package PACKAGE_NAME
 * @date 2019/11/22 9:34
 * @Copyright
 */
public class LocalDateDemo {

    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        System.out.println(now);

        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);

        LocalTime noon = LocalTime.NOON;
        System.out.println(noon);



    }
}
