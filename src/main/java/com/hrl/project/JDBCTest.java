package com.hrl.project;

import com.hrl.project.entity.OrderDepth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class JDBCTest {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    /**
     * 插入用户-防止sql注入-可以返回该条记录的主键
     *
     * @return
     */
    public int addOrder(OrderDepth orderDepth) {
        String sql = "insert into order_depth(id,time,bid_price,bid_amount,ask_price,ask_amount) values(null,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int resRow = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                ps.setDate(1, new java.sql.Date(orderDepth.getTime().getTime()));
                ps.setString(2, orderDepth.getBidPrice());
                ps.setString(3, orderDepth.getBidAmount());
                ps.setString(4, orderDepth.getAskPrice());
                ps.setString(5, orderDepth.getAskAmount());

                return ps;
            }
        }, keyHolder);
        System.out.println("操作记录数：" + resRow + " 主键：" + keyHolder.getKey());
        return Integer.parseInt(keyHolder.getKey().toString());
    }
}
