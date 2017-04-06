package com.qi.common.constants;

/**
 * Class EchartsConstants
 *
 * @author 张麒 2016/9/12.
 * @version Description:
 */
public class EchartsConstants{

    public enum Type {
        pie, bar, line
    }

    public enum Boolean {
        TRUE(true),
        FALSE(false);

        Boolean(boolean value) {
            this.value = value;
        }

        private boolean value;

        public boolean getValue() {
            return value;
        }
    }

    public enum HorizAlign {
        left, center, right
    }

    public enum VerticalAlign {
        top, middle, bottom
    }
}
