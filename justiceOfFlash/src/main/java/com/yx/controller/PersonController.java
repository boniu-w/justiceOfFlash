package com.yx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wg
 * @Package com.yx.controller
 * @date 2019/10/12 15:07
 * @Copyright
 */
@Controller(value = "/personController")
public class PersonController {


    @RequestMapping(value = "/getUser.do")
    public void getUser() {
        System.out.println("-----");
    }
}
