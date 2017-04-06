package com.qi.porject.web;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Class WebRunner
 *
 * @author 张麒 2017/1/22.
 * @version Description:
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.qi.porject.web", "com.qi.common.spring"})
public class WebRunner {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebRunner.class, args);
    }
}
