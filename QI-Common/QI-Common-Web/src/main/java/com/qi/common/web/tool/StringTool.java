package com.qi.common.web.tool;

import com.qi.common.util.StringUtil;

/**
 * Class StringTool
 *
 * @author 张麒 2016/5/12.
 * @version Description:
 */
public class StringTool {

    public String subString(String text, int len) {

        if (StringUtil.isBlank(text))
            return text;

        char[] ch = text.toCharArray();
        StringBuilder safe = new StringBuilder(text.length());
        int computer = 0;
        for (char c : ch) {
            if (computer > len && len != -1) {
                safe.append("....");
                break;
            }
            safe.append(toSalfChar(c));
            computer++;
        }
        return StringUtil.trim(safe.toString());
    }

    private String toSalfChar(char c) {
        switch (c) {
            case '<':
                return "&lt;";
            case '>':
                return "&gt;";
            case '\'':
                return "&#39;";
            case '&':
                return "&amp;";
            case '"':
                return "&#34;";
            case '\n':
                return "<br/>";
            default:
                return String.valueOf(c);
        }
    }
}
