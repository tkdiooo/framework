package com.qi.menu.web.service.write.impl;

import com.qi.common.database.dao.DBType;
import com.qi.common.database.datasource.dynamic.ReadWriteDataSource;
import com.qi.common.tool.Logger;
import com.qi.menu.model.domain.BasicSystem;
import com.qi.menu.web.dao.BasicSystemDAO;
import com.qi.menu.web.model.example.BasicSystemExample;
import com.qi.menu.web.service.write.SystemWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class PortalWriteServiceImpl
 *
 * @author 张麒 2016/9/2.
 * @version Description:
 */
@ReadWriteDataSource(DBType.WRITE)
@Service("PortalWriteService")
public class SystemWriteServiceImpl implements SystemWriteService {

    private static final Logger logger = new Logger(SystemWriteServiceImpl.class);

    @Autowired
    private BasicSystemDAO dao;

    @Override
    public void register(BasicSystem condition) {
        BasicSystemExample example = dao.getExample();
        example.createCriteria().andCodeEqualTo(condition.getCode());
        BasicSystem old = dao.getByExample(example);
        if (null == old) {
            dao.insertGetPrimaryKey(condition);
        } else {
            condition.setGuid(old.getGuid());
            dao.updateByPrimaryKeySelective(condition);
        }
    }

    @Override
    public void updateStatus(String code, Integer status) {
        BasicSystemExample example = dao.getExample();
        example.createCriteria().andCodeEqualTo(code);
        BasicSystem condition = dao.getByExample(example);
        condition.setStatus(status);
        dao.updateByPrimaryKeySelective(condition);
    }
}
