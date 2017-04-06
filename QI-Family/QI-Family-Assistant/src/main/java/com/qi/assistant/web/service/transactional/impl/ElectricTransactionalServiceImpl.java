package com.qi.assistant.web.service.transactional.impl;

import com.qi.assistant.web.config.ApplicationConfig;
import com.qi.assistant.web.dao.ElectricDAO;
import com.qi.assistant.web.dao.ElectricDetailsDAO;
import com.qi.assistant.web.dao.OptionsDAO;
import com.qi.assistant.web.model.example.ElectricDetailsExample;
import com.qi.assistant.web.model.example.OptionsExample;
import com.qi.assistant.web.model.model.Electric;
import com.qi.assistant.web.model.model.ElectricDetails;
import com.qi.assistant.web.model.model.Options;
import com.qi.assistant.web.model.vo.ElectricVO;
import com.qi.assistant.web.service.transactional.ElectricTransactionalService;
import com.qi.common.constants.StatusConstants;
import com.qi.common.tool.Logger;
import com.qi.common.util.BeanUtil;
import com.qi.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class ElectricTransactionalServiceImpl
 *
 * @author 张麒 2016/8/25.
 * @version Description:
 */
@Transactional
@Service("ElectricTransactionalService")
public class ElectricTransactionalServiceImpl implements ElectricTransactionalService {

    private static final Logger logger = new Logger(ElectricTransactionalServiceImpl.class);

    @Autowired
    private ElectricDAO dao;

    @Autowired
    private ElectricDetailsDAO detailsDao;

    @Autowired
    private OptionsDAO opDao;

    // 设置上一次单据的状态，如果有下次单据状态为有效无法编辑和删除
    private void setIsvalid(Long beforeid, Boolean isvalid) {
        Electric before = dao.selectByPrimaryKey(beforeid);
        before.setIsvalid(isvalid);
        ElectricVO VO = new ElectricVO();
        BeanUtil.copyPropertiesNotEmpty(VO, before);
        dao.updateByPrimaryKeySelective(VO);
    }

    @Override
    public void save(ElectricVO condition) {
        // 每年封账后，阶梯可用量修正归零
        if (null != condition.getIsreset() && condition.getIsreset()) {
            OptionsExample example = opDao.getExample();
            OptionsExample.Criteria criteria = example.createCriteria();
            criteria.andOptionstypeEqualTo(Electric.class.getSimpleName());
            criteria.andGradeEqualTo(1);
            List<Options> list = opDao.selectByExample(example);
            if (list.size() == 1) {
                Options options = list.get(0);
                options.setAmend(0);
                opDao.updateByPrimaryKeySelective(options);
            }
        }
        Long pk;
        if (null == condition.getIsreset()) {
            condition.setIsreset(false);
        }
        // 编辑
        if (null != condition.getGuid()) {
            Electric old = dao.selectByPrimaryKey(condition.getGuid());
            BeanUtil.copyPropertiesNotEmpty(old, condition);
            dao.updateByPrimaryKeySelective(old);
            ElectricDetailsExample example = detailsDao.getExample();
            example.createCriteria().andElectricguidEqualTo(old.getGuid());
            detailsDao.deleteByExample(example);
            pk = old.getGuid();
            System.out.println(pk);
            // TODO 记录日志
        } else {
            if (null != condition.getBeforeid()) {
                setIsvalid(condition.getBeforeid(), StatusConstants.Status.VALID.getKey());
            }
            condition.setIsvalid(StatusConstants.Status.INVALID.getKey());
            condition.setBatchnumber(ApplicationConfig.ELECTRIC_NUMBER + condition.getBilldate().substring(0, 4));
            condition.setCreatetime(DateUtil.getCurrentDate());
            condition.setCreator(1L);
            pk = dao.insertGetPrimaryKey(condition);
            System.out.println(pk);
            // TODO 记录日志
        }
        for (ElectricDetails details : condition.getChildren()) {
            if (null != details.getPeakamount()) {
                details.setElectricguid(pk);
                detailsDao.insertGetPrimaryKey(details);
            }
        }
    }

    @Override
    public void delete(Long guid) {
        Electric model = dao.selectByPrimaryKey(guid);
        if (null != model.getBeforeid()) {
            setIsvalid(model.getBeforeid(), StatusConstants.Status.INVALID.getKey());
        }
        ElectricDetailsExample example = detailsDao.getExample();
        example.createCriteria().andElectricguidEqualTo(guid);
        detailsDao.deleteByExample(example);
        dao.deleteByPrimaryKey(guid);
    }
}
