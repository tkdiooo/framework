package com.qi.dict.web.tool;

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
            // 字典管理模块页面
            case 0:
                // 显示全选Checkbox
                header.setCheckbox(true);
                // 标题4列
                map.put("parentcode", new TitleVO("parentcode", "父级编号", "父级编号", true));
                map.put("dictcode", new TitleVO("dictcode", "字典编号", "字典编号", true));
                map.put("dictname", new TitleVO("dictname", "字典名称", "字典名称"));
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
