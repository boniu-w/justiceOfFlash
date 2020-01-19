package com.yx.service;

import com.yx.bean.Person;
import com.yx.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wg
 * @Package com.yx.service
 * @date 2019/10/12 15:08
 * @Copyright
 */
@Service
public class PersonService {

    @Autowired
    private PersonDao personDao;


}
