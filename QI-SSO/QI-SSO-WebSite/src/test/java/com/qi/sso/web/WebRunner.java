package com.qi.sso.web;

import com.qi.common.constants.CommonConstants;
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

        String CONFIG_PATH = "D:\\IntelliJ IDEA\\Projects\\tkdiooo\\framework\\trunk\\config\\";

        System.setProperty(CommonConstants.RESOURCES_CONFIG_PATH, CONFIG_PATH);

        Tomcat tomcat = new Tomcat();

        String baseDir = new File("").getAbsolutePath();

        tomcat.setBaseDir(baseDir + "/QI-SSO/QI-SSO-WebSite/src/test/resources/tomcat-home");
        tomcat.enableNaming();
        tomcat.addWebapp("/portal", baseDir + "/QI-SSO/QI-SSO-WebSite/src/main/webapp");

        tomcat.setPort(8090);
        tomcat.start();
        tomcat.getServer().await();
    }
}
