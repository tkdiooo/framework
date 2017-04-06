package com.qi.resources;

import org.apache.catalina.startup.Tomcat;

import java.io.File;

/**
 * Class WebRunner
 *
 * @author 张麒 2016/5/17.
 * @version Description:
 */
public class WebRunner {

    public static void main(String[] args) throws Exception {

//        System.setProperty("resources.config.path",
//                "D:\\Project\\projects\\e-HR\\branches\\branch1.1.4\\01configs\\eHR-timemgmt\\timemgmt-website\\config\\");

        Tomcat tomcat = new Tomcat();

        String baseDir = new File("").getAbsolutePath();

        tomcat.setBaseDir(baseDir + "/QI-Static/QI-Static-Global/src/test/resources/tomcat-home");
        tomcat.enableNaming();
        tomcat.addWebapp("/static-global", baseDir + "/QI-Static/QI-Static-Global/src/main/webapp");

        tomcat.setPort(8080);
        tomcat.start();
        tomcat.getServer().await();
    }
}
