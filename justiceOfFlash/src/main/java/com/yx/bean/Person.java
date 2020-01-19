package com.yx.bean;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author wg
 * @Package PACKAGE_NAME
 * @date 2019/10/12 14:37
 * @Copyright
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Person {

    @Value("xiaomi")
    private String name;

    @Value("23")
    private Integer age;

    @Value("${person.nickName}")
    private String nickName;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
