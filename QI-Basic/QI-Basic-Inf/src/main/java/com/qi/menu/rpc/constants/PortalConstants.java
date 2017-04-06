package com.qi.menu.rpc.constants;

import com.qi.common.constants.inf.OptionEnum;

/**
 * Class PortalConstants
 *
 * @author 张麒 2016/9/1.
 * @version Description:
 */
public class PortalConstants {

    public enum Status implements OptionEnum<Integer, String> {

        Normal(0, "正常运行"),
        Upkeep(1, "维护更新"),
        Shutdown(2, "下线停运");

        Status(Integer key, String value) {
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

    public enum Type implements OptionEnum<Integer, String> {

        Menu(0, "Menu"),
        Website(1, "Website"),
        BBS(2, "BBS");

        Type(Integer key, String value) {
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
