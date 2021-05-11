package com.hrl.project;

import com.hrl.project.entity.OrderDepth;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Date;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        RecordOrderDepth recordOrderDepth = applicationContext.getBean(RecordOrderDepth.class);
        recordOrderDepth.recordOrderDepth();

        JDBCTest jdbcTest = applicationContext.getBean(JDBCTest.class);
        OrderDepth orderDepth = new OrderDepth();
        orderDepth.setAskAmount("1");
        orderDepth.setAskPrice("1");
        orderDepth.setBidAmount("1");
        orderDepth.setBidPrice("1");
        orderDepth.setTime(new Date());
        jdbcTest.addOrder(orderDepth);
    }

}