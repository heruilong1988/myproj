package com.hrl.project;

import com.hrl.project.entity.OrderDepth;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Date;

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
        RecordOrderDepth recordOrderDepth = applicationContext.getBean(RecordOrderDepth.class);
        recordOrderDepth.recordOrderDepth();

        JDBCTest jdbcTest = applicationContext.getBean(JDBCTest.class);

       /* OrderDepth orderDepth = OrderDepth.builder().askAmount("1").askPrice("1")
                .bidAmount("1").bidPrice("1").time(new Date()).current("current").build();
*/
    }

}