package com.qi.sso.server;

import org.apache.catalina.startup.Tomcat;

import java.io.File;

/**
 * Class WebRunner
 *
 * @author 张麒 2016/6/6.
 * @version Description:
 */
public class WebRunner {

    public static void main(String[] args) throws Exception {

        String CONFIG_PATH = "D:\\IntelliJ IDEA\\Projects\\tkdiooo\\framework\\trunk\\config\\";

        System.setProperty("resources.config.path", CONFIG_PATH);

        Tomcat tomcat = new Tomcat();

        String baseDir = new File("").getAbsolutePath();

        tomcat.setBaseDir(baseDir + "/QI-SSO/QI-SSO-Server/src/test/resources/tomcat-home");
        tomcat.enableNaming();
        tomcat.addWebapp("/authority", baseDir + "/QI-SSO/QI-SSO-Server/src/main/webapp");

        tomcat.setPort(8091);
        tomcat.start();
        tomcat.getServer().await();
    }
}
