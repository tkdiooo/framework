package com.qi.common.model.model;

import com.qi.common.constants.JdbcConstants.Driver;
import com.qi.common.constants.LabelConstants;

/**
 * Class DBConfigModel
 *
 * @author 张麒 2016/9/28.
 * @version Description:
 */
public class DBConfigModel {

    private String ippost;
    private String dbname;
    private String dbuser;
    private String dbpassword;
    private Driver driver;

    public String getIppost() {
        return ippost;
    }

    public void setIppost(String ippost) {
        this.ippost = ippost;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String getDbuser() {
        return dbuser;
    }

    public void setDbuser(String dbuser) {
        this.dbuser = dbuser;
    }

    public String getDbpassword() {
        return dbpassword;
    }

    public void setDbpassword(String dbpassword) {
        this.dbpassword = dbpassword;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getJdbcUrl() {
        String url = "";
        switch (driver) {
            case MySQL:
                url = "jdbc" + LabelConstants.COLON + "mysql" + LabelConstants.COLON + LabelConstants.DOUBLE_SLASH + this.ippost + LabelConstants.SLASH + this.dbname + LabelConstants.QUESTION + "useUnicode" + LabelConstants.EQUAL + LabelConstants.TRUE + LabelConstants.AMPERSAND + "characterEncoding" + LabelConstants.EQUAL + LabelConstants.UTF8;
                break;

        }
        return url;
    }
}
