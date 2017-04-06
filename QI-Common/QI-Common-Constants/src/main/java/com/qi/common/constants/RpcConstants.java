package com.qi.common.constants;

import com.qi.common.constants.inf.OptionEnum;

/**
 * Class RpcConstants
 *
 * @author 张麒 2016/9/1.
 * @version Description:
 */
public class RpcConstants {

    public static final boolean SUCCESSFUL = true;

    public static final boolean FAILURE = false;

    public enum ReplyCode implements OptionEnum<Integer, String> {

        Code200(200, "OK"),
        Code300(300, "Route change"),
        Code400(400, "Client Error"),
        Code500(500, "Server Error");

        ReplyCode(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        private Integer key;
        private String value;

        @Override
        public Integer getKey() {
            return key;
        }

        @Override
        public String getValue() {
            return value;
        }

        public static String getValueByKey(Integer key) {
            return OptionEnum.findValue(values(), key);
        }

        public static Integer getKeyByValue(String value) {
            return OptionEnum.findKey(values(), value);
        }
    }

}
