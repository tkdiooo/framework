/**
 *
 */
package com.qi.common.util;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * Class StringEscapeUtil
 *
 * @author 张麒 2016年3月28日
 * @version Description：
 */
public class StringEscapeUtil extends StringEscapeUtils {

    /**
     * Html字符串编码，以适应页面显示
     * <pre>
     * StringUtil.htmlEncode("<code><</code>a href="javascript:void(0)">取消&lt/a>") = <b>&<b>lt;a<b>&<b>nbsp;href=<b>&<b>quot;javascript:void(0)<b>&<b>quot;<b>&<b>gt;取消<b>&<b>lt;/a<b>&<b>gt
     * </pre>
     *
     * @param html html
     * @return String
     */
    public static String htmlEncode(String html) {
        if (StringUtil.isNotBlank(html))
            return html.replaceAll("&", "&amp;").replaceAll("\"", "&quot;").replaceAll("'", "‘").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
                    .replaceAll(" ", "&nbsp;").replaceAll("\\n", "<br>");
        else
            return "";
    }

    /**
     * html字符解码
     * <pre>
     * StringUtil.htmlEncode("<b>&<b>lt;a<b>&<b>nbsp;href=<b>&<b>quot;javascript:void(0)<b>&<b>quot;<b>&<b>gt;取消<b>&<b>lt;/a<b>&<b>gt") = <code><</code>a href="javascript:void(0)">取消&lt/a>
     * </pre>
     *
     * @param html html
     * @return String
     */
    public static String htmlDecode(String html) {
        if (StringUtil.isNotBlank(html))
            return html.replaceAll("&amp;", "&").replaceAll("&quot;", "\"").replaceAll("‘", "'").replaceAll("&lt;", "<").replaceAll("&gt;", ">")
                    .replaceAll("&nbsp;", " ").replaceAll("<br>", "\\n");
        else
            return "";
    }
}
