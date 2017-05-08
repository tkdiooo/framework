package com.qi.menu.web.service.read.impl;

import com.qi.common.database.dao.DBType;
import com.qi.common.database.datasource.dynamic.ReadWriteDataSource;
import com.qi.common.model.model.DBConfigModel;
import com.qi.common.model.vo.tree.ZTreeVO;
import com.qi.common.util.ListUtil;
import com.qi.menu.web.dao.BasicEntityDAO;
import com.qi.menu.web.service.read.EntityReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Class EntityReadServiceImpl
 *
 * @author 张麒 2016/9/28.
 * @version Description:
 */
@ReadWriteDataSource(DBType.READ)
@Service("EntityReadService")
public class EntityReadServiceImpl implements EntityReadService {

    @Autowired
    private BasicEntityDAO dao;

    @Override
    public List<ZTreeVO> findTableNameByDBName(DBConfigModel configModel) {
        List<ZTreeVO> dataSet = new ArrayList<>();
        List<String> list = dao.findTableNameByDBName(configModel);
        list.forEach(t -> dataSet.add(new ZTreeVO(t, "0", t, false)));
        return dataSet;
    }
}
