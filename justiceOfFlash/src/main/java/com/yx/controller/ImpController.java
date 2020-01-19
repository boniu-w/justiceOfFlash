package com.yx.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wg
 * @Package com.yx.controller
 * @date 2019/10/21 16:40
 * @Copyright
 */
public class ImpController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("implements Controller---");
        return null;
    }
}
