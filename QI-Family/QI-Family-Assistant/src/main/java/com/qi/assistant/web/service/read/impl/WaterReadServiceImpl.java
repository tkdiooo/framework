package com.qi.assistant.web.service.read.impl;

import com.qi.assistant.web.dao.WaterDAO;
import com.qi.assistant.web.dao.WaterDetailsDAO;
import com.qi.assistant.web.model.example.WaterDetailsExample;
import com.qi.assistant.web.model.example.WaterExample;
import com.qi.assistant.web.model.model.Water;
import com.qi.assistant.web.model.model.WaterDetails;
import com.qi.assistant.web.model.vo.OptionsVO;
import com.qi.assistant.web.service.read.WaterReadService;
import com.qi.assistant.web.tool.Header;
import com.qi.common.constants.StatusConstants;
import com.qi.common.database.dao.DBType;
import com.qi.common.database.datasource.dynamic.ReadWriteDataSource;
import com.qi.common.model.model.PagingModel;
import com.qi.common.model.vo.paging.OperateVO;
import com.qi.common.model.vo.paging.PagingVO;
import com.qi.common.model.vo.paging.TitleVO;
import com.qi.common.model.vo.select.SelectVO;
import com.qi.common.util.DateUtil;
import com.qi.common.util.ListUtil;
import com.qi.common.util.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Class WaterReadServiceImpl
 *
 * @author 张麒 2016/8/30.
 * @version Description:
 */
@ReadWriteDataSource(DBType.READ)
@Service("WaterReadService")
public class WaterReadServiceImpl implements WaterReadService {

    @Autowired
    private WaterDAO dao;

    @Autowired
    private WaterDetailsDAO detailsDao;

    @Override
    public PagingVO queryPagination(PagingModel<Water> pageInfo) {
        // 分页查询
        dao.queryPagination(pageInfo);
        // List集合转换为map集合
        pageInfo.setMapResult(ListUtil.getInstance());
        pageInfo.getResult().forEach((VO) -> {
            Map<String, Object> map = MapUtil.toMap(VO);
            // 数据已生效，不可修改、删除
            if (VO.getIsvalid()) {
                map.put(TitleVO.fieldType.operate.name(), new OperateVO());
            }
            // 初始数据，只能删除，不能修改
            else if (null == VO.getBeforeid()) {
                map.put(TitleVO.fieldType.operate.name(), new OperateVO(OperateVO.operateType.delete));
            }
            // 数据还未生效，可以修改、删除
            else {
                map.put(TitleVO.fieldType.operate.name(), new OperateVO(OperateVO.operateType.edited, OperateVO.operateType.delete));
            }
            map.put("是否封账", StatusConstants.YesNo.getValueByKey(VO.getIsreset()));
            pageInfo.getMapResult().add(map);
        });
        pageInfo.getResult().clear();
        PagingVO<Water> VO = new PagingVO<>();
        VO.setPagingModel(pageInfo);
        VO.setHeader(Header.getInstance().buildHeader(Water.class.getSimpleName()));
        return VO;
    }

    @Override
    public Water getModelByGuid(Long guid) {
        return dao.selectByPrimaryKey(guid);
    }

    @Override
    public boolean checkUnique(Water model) {
        WaterExample example = dao.getExample();
        example.createCriteria().andBilldateEqualTo(model.getBilldate());
        return dao.checkUnique(example, "guid", model.getGuid());
    }

    @Override
    public List<SelectVO> findYear() {
        List<String> result = dao.findYear();
        List<SelectVO> list = ListUtil.getInstance();
        result.forEach((Str) -> list.add(new SelectVO(Str, Str)));
        return list;
    }

    @Override
    public List<WaterDetails> findWaterDetailsByFKGuid(Long guid) {
        WaterDetailsExample example = detailsDao.getExample();
        example.createCriteria().andWaterguidEqualTo(guid);
        return detailsDao.selectByExample(example);
    }

    @Override
    public OptionsVO getOptions(OptionsVO options, Water water) {
        Integer count = dao.countByQuantity(dao.getLatestReset());
        options.setUseUp(null != count ? count + options.getAmend() : options.getAmend());
        if (null == water) {
            water = dao.getLatestModel();
            if (null != water) {
                options.setBeforeid(water.getGuid());
                options.setBilldate(DateUtil.format(water.getNextdate(), "yyyy-MM"));
                options.setCurrentdate(water.getNextdate());
                options.setPrevQuantity(water.getQuantity());
                options.setBeforebalance(water.getCurrentbalance());
                options.setCurrentbalance(new BigDecimal(0.00));
            } else {
                options.setBeforebalance(new BigDecimal(0.00));
                options.setCurrentbalance(new BigDecimal(0.00));
            }
        }
        // 编辑
        else {
            Water before = dao.selectByPrimaryKey(water.getBeforeid());
            options.setBeforeid(water.getBeforeid());
            options.setBilldate(water.getBilldate());
            options.setCurrentdate(water.getCurrentdate());
            options.setNextdate(water.getNextdate());
            options.setPrevQuantity(before.getQuantity());
            options.setUseQuantity(water.getQuantity() - before.getQuantity());
            options.setBeforebalance(water.getBeforebalance());
            options.setCurrentbalance(water.getCurrentbalance());
            options.setUseUp(options.getUseUp() - options.getUseQuantity());
        }
        return options;
    }

}
