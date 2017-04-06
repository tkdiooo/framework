package com.qi.assistant.web.config;

import com.qi.common.constants.inf.OptionEnum;

/**
 * Class ApplicationConfig
 *
 * @author 张麒 2016/6/21.
 * @version Description:
 */
public class ApplicationConfig {

    public enum FamilyOptions implements OptionEnum<String, String> {
        Gas("Gas", "燃气单设置"),
        Water("Water", "水费单设置"),
        Electric("Electric", "电费单设置");

        FamilyOptions(String key, String value) {
            this.key = key;
            this.value = value;
        }

        private String key;
        private String value;

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public String getValue() {
            return value;
        }

        public static String getValueByKey(String key) {
            return OptionEnum.findValue(values(), key);
        }

        public static String getKeyByValue(String value) {
            return OptionEnum.findKey(values(), value);
        }
    }

    /**
     * 燃气批号
     */
    public static final String GAS_NUMBER = "39-39371919-";

    public static final String ELECTRIC_NUMBER = "1214267331-";

    public static final String WATER_NUMBER = "329104-";
}
