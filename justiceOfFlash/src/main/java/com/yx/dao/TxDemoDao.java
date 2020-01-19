package com.yx.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author wg
 * @Package com.yx.dao
 * @date 2019/10/28 10:37
 * @Copyright
 */
@Repository
public class TxDemoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Transactional
    public void insert() {
        String insert = "INSERT INTO cst_customer (cust_name, cust_level) VALUES (?,?);";

        String substring = UUID.randomUUID().toString().substring(0, 4);
//        int v = (int) Math.floor(Math.random() * 5) ;

        jdbcTemplate.update(insert, substring, 1);

        int i = 10 / 0;
    }
}
