package com.yx.service.impl;

import com.yx.service.Filter;
import org.springframework.stereotype.Service;

/**
 * @author wg
 * @Package com.yx.service.impl
 * @date 2020/1/19 15:00
 * @Copyright
 */
@Service
public class UrlFilter implements Filter {


    @Override
    public String setFilterName() {
        System.out.println("url filter");
        return null;
    }
}
