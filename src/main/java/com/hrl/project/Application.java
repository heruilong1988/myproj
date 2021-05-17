package com.hrl.project;

import com.hrl.project.dao.OrderDepthDAO;
import com.hrl.project.entity.OrderDepth;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.Date;

@MapperScan("com.hrl.project.mapper")
@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        // HTTP 代理，只能代理 HTTP 请求
        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "8118");

        // HTTPS 代理，只能代理 HTTPS 请求
        System.setProperty("https.proxyHost", "127.0.0.1");
        System.setProperty("https.proxyPort", "8118");


        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
       /* RecordOrderDepth recordOrderDepth = applicationContext.getBean(RecordOrderDepth.class);
        recordOrderDepth.recordOrderDepth();*/

        //JDBCTest jdbcTest = applicationContext.getBean(JDBCTest.class);
        OrderDepthDAO orderDepthDAO = applicationContext.getBean(OrderDepthDAO.class);


        BigDecimal bigDecimal = new BigDecimal("1");
        OrderDepth orderDepth = OrderDepth.builder().askAmount_100(bigDecimal).askPrice_100(bigDecimal)
                .bidAmount_100(bigDecimal).bidPrice_100(bigDecimal).time(new Date()).base("btc").target("usdt")
                .askAmount_500(bigDecimal).askPrice_500(bigDecimal).bidAmount_500(bigDecimal).bidPrice_500(bigDecimal)
                .build();

        orderDepthDAO.saveOrderDepth(orderDepth);



    }

}