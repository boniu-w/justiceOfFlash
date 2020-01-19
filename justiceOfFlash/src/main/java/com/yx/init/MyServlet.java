package com.yx.init;

//import com.yx.config.AppConfig;
import com.yx.config.BeanConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * @author wg
 * @Package com.yx.init
 * @date 2019/10/18 14:52
 * @Copyright
 */
public class MyServlet implements WebApplicationInitializer {


    @Override
    public void onStartup(ServletContext servletCxt) {

        System.out.println("init--------");

        // Load Spring web application configuration
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(BeanConfig.class);
        ac.refresh();

        // Create and register the DispatcherServlet
        DispatcherServlet servlet = new DispatcherServlet(ac);
        ServletRegistration.Dynamic registration = servletCxt.addServlet("beanConfig", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("*.do");
    }
}


