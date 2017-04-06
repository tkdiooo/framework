package com.qi.common.basic.controller;

import com.qi.common.basic.service.BasicReadService;
import com.qi.common.model.model.PagingModel;
import com.qi.common.util.ResponseUtil;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class BasicController
 *
 * @author 张麒 2016/7/27.
 * @version Description:
 */
public abstract class BasicController<T> {

    protected abstract BasicReadService<T> getReadService();

    @RequestMapping(value = "queryPagination.html")
    public String waterPagination(ModelMap model, PagingModel<T> pageInfo, T condition) {
        pageInfo.setParam(condition);
        model.put("pagingVO", getReadService().queryPagination(pageInfo));
        model.put("layout", "layout/empty.vm");
        return "macro/grid";
    }

    @RequestMapping(value = "checkUnique.ajax")
    public void checkGrade(T condition, HttpServletResponse response) throws IOException {
        ResponseUtil.writeText(String.valueOf(getReadService().checkUnique(condition)), response);
    }
}
