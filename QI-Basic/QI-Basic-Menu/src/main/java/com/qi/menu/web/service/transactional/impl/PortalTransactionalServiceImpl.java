package com.qi.menu.web.service.transactional.impl;

import com.qi.common.tool.Logger;
import com.qi.common.util.BeanUtil;
import com.qi.menu.model.domain.BasicSystem;
import com.qi.menu.web.dao.BasicSystemDAO;
import com.qi.menu.web.service.transactional.PortalTransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class PortalTransactionalServiceImpl
 *
 * @author 张麒 2016/9/2.
 * @version Description:
 */
@Transactional
@Service("PortalTransactionalService")
public class PortalTransactionalServiceImpl implements PortalTransactionalService {

    private static final Logger logger = new Logger(PortalTransactionalServiceImpl.class);

    @Autowired
    private BasicSystemDAO dao;

    @Override
    public void save(BasicSystem condition) {
        // 编辑
        if (null != condition.getGuid()) {
            BasicSystem old = dao.selectByPrimaryKey(condition.getGuid());
            BeanUtil.copyPropertiesNotEmpty(old, condition);
            dao.updateByPrimaryKeySelective(old);
            // TODO 记录日志
        } else {
//            condition.setStatus(StatusConstants.YesNo.Yes.getKey());
            Long pk = dao.insertGetPrimaryKey(condition);
            System.out.println(pk);
            // TODO 记录日志
        }
    }
}
