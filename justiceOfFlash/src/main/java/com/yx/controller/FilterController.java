package com.yx.controller;

import com.yx.service.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wg
 * @Package com.yx.controller
 * @date 2020/1/19 15:32
 * @Copyright
 */
@Controller
@RequestMapping(value = "/filterController")
public class FilterController {


    @Qualifier("chartsetFilter")
    @Autowired
    Filter filter;

    public void test1(){

        filter.setFilterName();
    }
}
