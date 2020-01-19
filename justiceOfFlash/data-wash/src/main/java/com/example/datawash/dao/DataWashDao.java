package com.example.datawash.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author wg
 * @Package com.example.datawash.dao
 * @date 2019/12/10 14:09
 * @Copyright
 */
@Repository
public class DataWashDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Transactional
    public List examine() {
        String insert = "select * from user;";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(insert);


//        String substring = UUID.randomUUID().toString().substring(0, 4);
//        int v = (int) Math.floor(Math.random() * 5) ;

//        jdbcTemplate.update(insert, substring, 1);

//        int i = 10 / 0;

        return mapList;
    }
}
