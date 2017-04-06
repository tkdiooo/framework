package com.qi.assistant.web.service.read.impl;

import com.qi.assistant.web.dao.ElectricDAO;
import com.qi.assistant.web.dao.ElectricDetailsDAO;
import com.qi.assistant.web.model.example.ElectricDetailsExample;
import com.qi.assistant.web.model.example.ElectricExample;
import com.qi.assistant.web.model.model.Electric;
import com.qi.assistant.web.model.model.ElectricDetails;
import com.qi.assistant.web.model.vo.OptionsVO;
import com.qi.assistant.web.service.read.ElectricReadService;
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
 * Class ElectricReadServiceImpl
 *
 * @author 张麒 2016/8/11.
 * @version Description:
 */
@ReadWriteDataSource(DBType.READ)
@Service("ElectricReadService")
public class ElectricReadServiceImpl implements ElectricReadService {

    @Autowired
    private ElectricDAO dao;

    @Autowired
    private ElectricDetailsDAO detailsDao;

    @Override
    public PagingVO queryPagination(PagingModel<Electric> pageInfo) {
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
        PagingVO<Electric> VO = new PagingVO<>();
        VO.setPagingModel(pageInfo);
        VO.setHeader(Header.getInstance().buildHeader(Electric.class.getSimpleName()));
        return VO;
    }

    @Override
    public Electric getModelByGuid(Long guid) {
        return dao.selectByPrimaryKey(guid);
    }

    @Override
    public boolean checkUnique(Electric model) {
        ElectricExample example = dao.getExample();
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
    public List<ElectricDetails> findElectricDetailsByFKGuid(Long guid) {
        ElectricDetailsExample example = detailsDao.getExample();
        example.createCriteria().andElectricguidEqualTo(guid);
        return detailsDao.selectByExample(example);
    }

    @Override
    public OptionsVO getOptions(OptionsVO options, Electric electric) {
        Integer count = dao.countByQuantity(dao.getLatestReset());
        options.setUseUp(null != count ? count + options.getAmend() : options.getAmend());
        if (null == electric) {
            electric = dao.getLatestModel();
            if (null != electric) {
                options.setBeforeid(electric.getGuid());
                options.setBilldate(DateUtil.format(electric.getNextdate(), "yyyy-MM"));
                options.setCurrentdate(electric.getNextdate());
                options.setPrevQuantity(electric.getPeakquantity());
                options.setPrevQuantityT(electric.getFlatquantity());
                options.setBeforebalance(electric.getCurrentbalance());
                options.setCurrentbalance(new BigDecimal(0.00));
            } else {
                options.setBeforebalance(new BigDecimal(0.00));
                options.setCurrentbalance(new BigDecimal(0.00));
            }
        }
        // 编辑
        else {
            Electric before = dao.selectByPrimaryKey(electric.getBeforeid());
            options.setBeforeid(electric.getBeforeid());
            options.setBilldate(electric.getBilldate());
            options.setCurrentdate(electric.getCurrentdate());
            options.setNextdate(electric.getNextdate());
            options.setPrevQuantity(before.getPeakquantity());
            options.setPrevQuantityT(before.getFlatquantity());
            options.setUseQuantity(electric.getPeakquantity() - before.getPeakquantity());
            options.setUseQuantityT(electric.getFlatquantity() - before.getFlatquantity());
            options.setBeforebalance(electric.getBeforebalance());
            options.setCurrentbalance(electric.getCurrentbalance());
            options.setUseUp(options.getUseUp() - (options.getUseQuantity() + options.getUseQuantityT()));
        }
        return options;
    }
}
