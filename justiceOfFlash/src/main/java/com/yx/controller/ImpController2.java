package com.yx.controller;

import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wg
 * @Package com.yx.controller
 * @date 2019/10/21 17:53
 * @Copyright
 */
public class ImpController2 implements HttpRequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("implements HttpRequestHandler ---");
    }
}
