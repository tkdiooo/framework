/**
 *
 */
package com.qi.common.constants;

import com.qi.common.constants.inf.OptionEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Class OperateConstants
 *
 * @author 张麒 2016年3月28日
 * @version Description：
 */
public class OperateConstants {

    /**
     * Class Account
     *
     * @author 张麒 2016年3月28日
     * @version Description：账号操作枚举
     */
    public enum Account implements OptionEnum<Integer, String> {

        /**
         * 禁用
         */
        DISABLE(0, "禁用"),

        /**
         * 启用
         */
        ENABLED(1, "启用"),

        /**
         * 锁定
         */
        LOCKED(0, "锁定"),

        /**
         * 解锁
         */
        UNLOCKED(1, "解锁");

        Account(Integer key, String value) {
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

        private static List<OptionEnum<Integer, String>> options = null;

        static {
            options = new ArrayList<OptionEnum<Integer, String>>();
            for (OptionEnum<Integer, String> option : values()) {
                options.add(option);
            }
        }

        public static List<OptionEnum<Integer, String>> getOptions() {
            return options;
        }
    }
}
