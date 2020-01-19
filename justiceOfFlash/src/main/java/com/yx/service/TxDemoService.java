package com.yx.service;

import com.yx.dao.TxDemoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wg
 * @Package com.yx.service
 * @date 2019/10/28 10:56
 * @Copyright
 */
@Service
public class TxDemoService {

    @Autowired
    private TxDemoDao txDemoDao;

    public void update(){
        txDemoDao.insert();
        System.out.println("更新完成...");
    }
}
