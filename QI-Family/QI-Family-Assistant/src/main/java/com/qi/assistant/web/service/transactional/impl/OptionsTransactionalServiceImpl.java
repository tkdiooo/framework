package com.qi.assistant.web.service.transactional.impl;

import com.qi.assistant.web.dao.OptionsDAO;
import com.qi.assistant.web.model.model.Options;
import com.qi.assistant.web.service.transactional.OptionsTransactionalService;
import com.qi.common.tool.Logger;
import com.qi.common.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class OptionsTransactionalServiceImpl
 *
 * @author 张麒 2016/7/25.
 * @version Description:
 */
@Transactional
@Service("OptionsTransactionalService")
public class OptionsTransactionalServiceImpl implements OptionsTransactionalService {

    private static final Logger logger = new Logger(OptionsTransactionalServiceImpl.class);

    @Autowired
    private OptionsDAO dao;

    @Override
    public void save(Options condition) {
        // 编辑
        if (null != condition.getGuid()) {
            Options old = dao.selectByPrimaryKey(condition.getGuid());
            BeanUtil.copyPropertiesNotEmpty(old, condition);
            dao.updateByPrimaryKeySelective(old);
            // TODO 记录日志
        } else {
            Long pk = dao.insertGetPrimaryKey(condition);
            System.out.println(pk);
            // TODO 记录日志
        }
    }
}
