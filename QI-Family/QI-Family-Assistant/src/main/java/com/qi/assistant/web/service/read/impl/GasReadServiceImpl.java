package com.qi.assistant.web.service.read.impl;

import com.qi.assistant.web.dao.GasDAO;
import com.qi.assistant.web.dao.GasDetailsDAO;
import com.qi.assistant.web.model.example.GasDetailsExample;
import com.qi.assistant.web.model.example.GasExample;
import com.qi.assistant.web.model.model.Gas;
import com.qi.assistant.web.model.model.GasDetails;
import com.qi.assistant.web.model.vo.OptionsVO;
import com.qi.assistant.web.service.read.GasReadService;
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
 * Class OptionsReadServiceImpl
 *
 * @author 张麒 2016/7/25.
 * @version Description:
 */
@ReadWriteDataSource(DBType.READ)
@Service("GasReadService")
public class GasReadServiceImpl implements GasReadService {

    @Autowired
    private GasDAO dao;

    @Autowired
    private GasDetailsDAO gdDao;

    @Override
    public PagingVO queryPagination(PagingModel<Gas> pageInfo) {
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
        PagingVO<Gas> VO = new PagingVO<>();
        VO.setPagingModel(pageInfo);
        VO.setHeader(Header.getInstance().buildHeader(Gas.class.getSimpleName()));
        return VO;
    }

    @Override
    public Gas getModelByGuid(Long guid) {
        return dao.selectByPrimaryKey(guid);
    }

    @Override
    public boolean checkUnique(Gas model) {
        GasExample example = dao.getExample();
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
    public List<GasDetails> findGasDetailsByGasGuid(Long gasGuid) {
        GasDetailsExample example = gdDao.getExample();
        example.createCriteria().andGasguidEqualTo(gasGuid);
        return gdDao.selectByExample(example);
    }

    @Override
    public OptionsVO getOptions(OptionsVO options, Gas gas) {
        Integer count = dao.countByGasQuantity(dao.getLatestReset());
        options.setUseUp(null != count ? count + options.getAmend() : options.getAmend());
        if (null == gas) {
            gas = dao.getLatestModel();
            if (null != gas) {
                options.setBeforeid(gas.getGuid());
                options.setBilldate(DateUtil.format(gas.getNextdate(), "yyyy-MM"));
                options.setCurrentdate(gas.getNextdate());
                options.setPrevQuantity(gas.getQuantity());
                options.setBeforebalance(gas.getBeforebalance());
                options.setCurrentbalance(gas.getCurrentbalance());
            } else {
                options.setBeforebalance(new BigDecimal(0.00));
                options.setCurrentbalance(new BigDecimal(0.00));
            }
        }
        // 编辑
        else {
            Gas before = dao.selectByPrimaryKey(gas.getBeforeid());
            options.setBeforeid(gas.getBeforeid());
            options.setBilldate(gas.getBilldate());
            options.setCurrentdate(gas.getCurrentdate());
            options.setNextdate(gas.getNextdate());
            options.setPrevQuantity(before.getQuantity());
            options.setUseQuantity(gas.getQuantity() - before.getQuantity());
            options.setBeforebalance(gas.getBeforebalance());
            options.setCurrentbalance(gas.getCurrentbalance());
            options.setUseUp(options.getUseUp() - options.getUseQuantity());
        }
        return options;
    }
}
