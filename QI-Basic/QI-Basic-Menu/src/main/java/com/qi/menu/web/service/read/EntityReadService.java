package com.qi.menu.web.service.read;

import com.qi.common.model.model.DBConfigModel;
import com.qi.common.model.vo.tree.ZTreeVO;

import java.util.List;

/**
 * Class EntityReadService
 *
 * @author 张麒 2016/9/28.
 * @version Description:
 */
public interface EntityReadService {

    List<ZTreeVO> findTableNameByDBName(DBConfigModel configModel);
}
