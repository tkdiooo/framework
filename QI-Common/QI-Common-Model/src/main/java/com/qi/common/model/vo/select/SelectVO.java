package com.qi.common.model.vo.select;

/**
 * Class SelectVO
 *
 * @author 张麒 2016/8/4.
 * @version Description:
 */
public class SelectVO {

    private String key;
    private String value;

    public SelectVO(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
