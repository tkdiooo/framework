package com.qi.assistant.web.service.transactional.impl;

import com.qi.assistant.web.config.ApplicationConfig;
import com.qi.assistant.web.dao.GasDAO;
import com.qi.assistant.web.dao.GasDetailsDAO;
import com.qi.assistant.web.dao.OptionsDAO;
import com.qi.assistant.web.model.example.GasDetailsExample;
import com.qi.assistant.web.model.example.OptionsExample;
import com.qi.assistant.web.model.model.Gas;
import com.qi.assistant.web.model.model.Options;
import com.qi.assistant.web.model.vo.GasVO;
import com.qi.assistant.web.service.transactional.GasTransactionalService;
import com.qi.common.constants.StatusConstants;
import com.qi.common.tool.Logger;
import com.qi.common.util.BeanUtil;
import com.qi.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class GasTransactionalServiceImpl
 *
 * @author 张麒 2016/8/3.
 * @version Description:
 */
@Transactional
@Service("GasTransactionalService")
public class GasTransactionalServiceImpl implements GasTransactionalService {

    private static final Logger logger = new Logger(GasTransactionalServiceImpl.class);

    @Autowired
    private GasDAO dao;

    @Autowired
    private GasDetailsDAO gdDao;

    @Autowired
    private OptionsDAO opDao;

    private void setIsvalid(Long beforeid, Boolean isvalid) {
        Gas before = dao.selectByPrimaryKey(beforeid);
        before.setIsvalid(isvalid);
        GasVO VO = new GasVO();
        BeanUtil.copyPropertiesNotEmpty(VO, before);
        dao.updateByPrimaryKeySelective(VO);
    }

    @Override
    public void save(GasVO condition) {
        // 每年封账后，阶梯可用量修正归零
        if (null != condition.getIsreset() && condition.getIsreset()) {
            OptionsExample example = opDao.getExample();
            OptionsExample.Criteria criteria = example.createCriteria();
            criteria.andOptionstypeEqualTo(Gas.class.getSimpleName());
            criteria.andGradeEqualTo(1);
            List<Options> list = opDao.selectByExample(example);
            if (list.size() == 1) {
                Options options = list.get(0);
                options.setAmend(0);
                opDao.updateByPrimaryKeySelective(options);
            }
        }
        // 编辑
        if (null != condition.getGuid()) {
            Gas old = dao.selectByPrimaryKey(condition.getGuid());
            BeanUtil.copyPropertiesNotEmpty(old, condition);
            dao.updateByPrimaryKeySelective(old);
            GasDetailsExample example = gdDao.getExample();
            example.createCriteria().andGasguidEqualTo(old.getGuid());
            gdDao.deleteByExample(example);
            condition.getChildren().forEach((VO) -> {
                if (null != VO.getGasamount()) {
                    VO.setGasguid(old.getGuid());
                    gdDao.insertGetPrimaryKey(VO);
                }
            });
            // TODO 记录日志
        } else {
            if (null != condition.getBeforeid()) {
                setIsvalid(condition.getBeforeid(), StatusConstants.Status.VALID.getKey());
            }
            condition.setIsvalid(StatusConstants.Status.INVALID.getKey());
            condition.setBatchnumber(ApplicationConfig.GAS_NUMBER + condition.getBilldate().substring(0, 4));
            condition.setCreatetime(DateUtil.getCurrentDate());
            condition.setCreator(1L);
            if (null == condition.getIsreset()) {
                condition.setIsreset(false);
            }
            Long pk = dao.insertGetPrimaryKey(condition);
            condition.getChildren().forEach((VO) -> {
                if (null != VO.getGasamount()) {
                    VO.setGasguid(pk);
                    gdDao.insertGetPrimaryKey(VO);
                }
            });
            System.out.println(pk);
            // TODO 记录日志
        }
    }

    @Override
    public void delete(Long guid) {
        Gas gas = dao.selectByPrimaryKey(guid);
        if (null != gas.getBeforeid()) {
            setIsvalid(gas.getBeforeid(), StatusConstants.Status.INVALID.getKey());
        }
        GasDetailsExample example = gdDao.getExample();
        example.createCriteria().andGasguidEqualTo(guid);
        gdDao.deleteByExample(example);
        dao.deleteByPrimaryKey(guid);
    }

}
