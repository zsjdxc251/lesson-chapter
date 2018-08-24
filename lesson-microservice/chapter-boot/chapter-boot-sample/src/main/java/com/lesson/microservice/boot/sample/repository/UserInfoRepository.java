package com.lesson.microservice.boot.sample.repository;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhengshijun
 * @version created on 2018/8/24.
 */
@Repository
public class UserInfoRepository {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserInfoRepository(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }


    public void find(){

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from city");

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void findAll(){

        List<Map<String,String>> listMap = jdbcTemplate.query("select * from user_info", resultSet -> {
            List<Map<String,String>> list = Lists.newArrayList();
            while (resultSet.next()) {
                list.add(ImmutableMap.of("username",resultSet.getString("username")));
            }
            return list;
        });
        System.out.println(JSON.toJSONString(listMap));
    }
}
