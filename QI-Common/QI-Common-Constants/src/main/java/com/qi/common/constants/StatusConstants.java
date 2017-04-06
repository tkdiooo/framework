/**
 *
 */
package com.qi.common.constants;

import com.qi.common.constants.inf.OptionEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Class StatusConstants
 *
 * @author 张麒 2016年3月27日
 * @version Description：状态常量类
 */
public class StatusConstants {

    public enum YesNo implements OptionEnum<Boolean, String> {

        Yes(true, "是"),
        No(false, "否");

        YesNo(Boolean key, String value) {
            this.key = key;
            this.value = value;
        }

        private Boolean key;
        private String value;

        @Override
        public Boolean getKey() {
            return key;
        }

        @Override
        public String getValue() {
            return value;
        }

        public static String getValueByKey(Boolean key) {
            return OptionEnum.findValue(values(), key);
        }

        public static Boolean getKeyByValue(String value) {
            return OptionEnum.findKey(values(), value);
        }
    }

    /**
     * Class DataStatus
     *
     * @author 张麒 2016年3月28日
     * @version Description：状态枚举
     */
    public enum Status implements OptionEnum<Boolean, String> {

        /**
         * 有效
         */
        VALID(true, "有效"),

        /**
         * 失效
         */
        INVALID(false, "失效");

        Status(Boolean key, String value) {
            this.key = key;
            this.value = value;
        }

        private Boolean key;
        private String value;

        @Override
        public Boolean getKey() {
            return key;
        }

        @Override
        public String getValue() {
            return value;
        }

        public static String getValueByKey(Boolean key) {
            return OptionEnum.findValue(values(), key);
        }

        public static Boolean getKeyByValue(String value) {
            return OptionEnum.findKey(values(), value);
        }

        private static List<OptionEnum<Boolean, String>> options = null;

        static {
            options = new ArrayList<OptionEnum<Boolean, String>>();
            for (OptionEnum<Boolean, String> option : values()) {
                options.add(option);
            }
        }

        public static List<OptionEnum<Boolean, String>> getOptions() {
            return options;
        }
    }

    /**
     * Class DictDefine
     *
     * @author 张麒 2016年3月28日
     * @version Description：字典界限
     */
    public enum DictDefine implements OptionEnum<Integer, String> {

        /**
         * 系统级
         */
        SYS_LEVEL(0, "系统级"),

        /**
         * 业务级
         */
        BIZ_LEVEL(1, "业务级");

        DictDefine(Integer key, String value) {
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
