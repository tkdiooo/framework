package com.qi.dict.web.service.transactional.impl;

import com.qi.common.constants.StatusConstants;
import com.qi.common.tool.Logger;
import com.qi.common.util.BeanUtil;
import com.qi.dict.web.config.ApplicationConfig;
import com.qi.dict.web.dao.DictionaryDAO;
import com.qi.dict.web.model.model.Dictionary;
import com.qi.dict.web.service.transactional.DictTransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class DictTransactionalServiceImpl
 *
 * @author 张麒 2016/7/25.
 * @version Description:
 */
@Transactional
@Service("DictTransactionalService")
public class DictTransactionalServiceImpl implements DictTransactionalService {

    private static final Logger logger = new Logger(DictTransactionalServiceImpl.class);

    @Autowired
    private DictionaryDAO dictDAO;

    @Override
    public void saveDict(Dictionary condition) {
        if (!condition.getParentcode().equals(ApplicationConfig.ROOT_CODE)) {
            condition.setDictcode(condition.getParentcode() + condition.getDictcode());
        }
        // 编辑
        if (null != condition.getGuid()) {
            Dictionary oldDict = dictDAO.selectByPrimaryKey(condition.getGuid());
            BeanUtil.copyPropertiesNotEmpty(oldDict, condition);
            dictDAO.updateByPrimaryKeySelective(oldDict);
            // TODO 记录日志
        } else {
            condition.setSort(0);
            condition.setStatus(StatusConstants.YesNo.Yes.getKey());
            Long pk = dictDAO.insertGetPrimaryKey(condition);
            System.out.println(pk);
            // TODO 记录日志
        }
    }
}
