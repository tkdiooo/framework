package com.qi.sso.model.dto;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.Map;

public class SessionInfo implements Serializable {

    private static final long serialVersionUID = 9128475035980902677L;


    private String loginName;


    private String jsonUserInfo;

    private UserAuthData userAuthData;


    public String getLoginName() {
        return loginName;
    }


    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }


    public String getJsonUserInfo() {
        return jsonUserInfo;
    }


    public void setJsonUserInfo(String jsonUserInfo) {
        this.jsonUserInfo = jsonUserInfo;
        this.userAuthData = JSON.parseObject(jsonUserInfo, UserAuthData.class);
    }

    public Map<String, Object> getMapUserInfo() {
        return JSON.parseObject(getJsonUserInfo());
    }

    public UserAuthData getUserAuthData() {
        return userAuthData;
    }
}
