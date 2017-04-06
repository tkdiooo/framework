package com.qi.common.velocity.template;

import javax.servlet.http.HttpServletRequest;

/**
 * Class TmplFuncs
 *
 * @author 张麒 2016/5/17.
 * @version Description:
 */
public interface TmplFuncs {
    String toHtml(HttpServletRequest request, String templateName);
}
