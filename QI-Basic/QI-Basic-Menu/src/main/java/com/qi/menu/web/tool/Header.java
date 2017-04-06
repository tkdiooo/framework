package com.qi.menu.web.tool;

import com.qi.common.model.vo.paging.HeaderVO;
import com.qi.common.model.vo.paging.TitleVO;
import com.qi.common.util.ThrowableUtil;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class Deploy
 *
 * @author 张麒 2016/6/27.
 * @version Description:
 */
public class Header {

    private Header() {
    }

    private static Header deploy = null;

    public static Header getInstance() {
        if (null == deploy)
            synchronized (Header.class) {
                if (null == deploy) deploy = new Header();
            }
        return deploy;
    }

    public HeaderVO buildHeader(int menuIndex) {
        HeaderVO header = new HeaderVO();
        Map<String, TitleVO> map = new LinkedHashMap<>();
        switch (menuIndex) {
            // 菜单管理模块页面
            case 0:
                // 显示全选Checkbox
                header.setCheckbox(true);
                // 标题7列
                map.put("systemcode", new TitleVO("systemcode", "系统编号", "系统编号", 200));
                map.put("menucode", new TitleVO("menucode", "菜单编码", "按菜单编码排序", true, 250));
                map.put("menuname", new TitleVO("menuname", "菜单名称", "菜单名称", 100));
                map.put("domain", new TitleVO("domain", "菜单域名", "菜单域名", 150));
                map.put("controller", new TitleVO("controller", "Controller路径", "Controller路径", 150));
                map.put("requestmapping", new TitleVO("requestmapping", "Request路径", "Request路径", 150));
                map.put("level", new TitleVO("level", "层级", "按层级排序", true, 80, TitleVO.fieldType.textNode));
                map.put("status", new TitleVO("status", "状态", "菜单状态", 80, TitleVO.fieldType.multiNode));
                map.put(TitleVO.fieldType.operate.name(), new TitleVO(TitleVO.fieldType.operate.name(), "", "", TitleVO.fieldType.operate));
                break;
            // 门户注册模块页面
            case 1:
                // 显示全选Checkbox
                header.setCheckbox(true);
                // 标题4列
                map.put("code", new TitleVO("code", "系统编号", "系统编号"));
                map.put("namecn", new TitleVO("namecn", "系统名称", "系统名称"));
                map.put("domain", new TitleVO("domain", "系统域名", "系统域名"));
                map.put("contextpath", new TitleVO("contextpath", "系统上下文", "系统上下文"));
                map.put("homepage", new TitleVO("homepage", "系统首页", "系统首页"));
                map.put("portaltype", new TitleVO("portaltype", "系统类型", "系统类型", 80, TitleVO.fieldType.multiNode));
                map.put("status", new TitleVO("status", "状态", "菜单状态", 80, TitleVO.fieldType.multiNode));
                map.put(TitleVO.fieldType.operate.name(), new TitleVO(TitleVO.fieldType.operate.name(), "", "", TitleVO.fieldType.operate));
                break;
            default:
                ThrowableUtil.throwRuntimeException("Can't find the matching menuIndex");
        }
        header.setTitle(map);
        return header;
    }
}
