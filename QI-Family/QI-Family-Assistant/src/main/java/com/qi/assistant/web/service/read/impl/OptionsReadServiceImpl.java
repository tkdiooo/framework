package com.qi.assistant.web.service.read.impl;

import com.qi.assistant.web.dao.OptionsDAO;
import com.qi.assistant.web.model.example.OptionsExample;
import com.qi.assistant.web.model.model.Options;
import com.qi.assistant.web.model.vo.OptionsVO;
import com.qi.assistant.web.service.read.OptionsReadService;
import com.qi.assistant.web.tool.Header;
import com.qi.common.constants.DateConstants;
import com.qi.common.database.dao.DBType;
import com.qi.common.database.datasource.dynamic.ReadWriteDataSource;
import com.qi.common.model.model.PagingModel;
import com.qi.common.model.vo.paging.PagingVO;
import com.qi.common.util.BeanUtil;
import com.qi.common.util.ListUtil;
import com.qi.common.util.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Class OptionsReadServiceImpl
 *
 * @author 张麒 2016/7/25.
 * @version Description:
 */
@ReadWriteDataSource(DBType.READ)
@Service("OptionsReadService")
public class OptionsReadServiceImpl implements OptionsReadService {

    @Autowired
    private OptionsDAO dao;

    @Override
    public PagingVO queryPagination(PagingModel<Options> pageInfo) {
        // 分页查询
        dao.queryPagination(pageInfo);
        // List集合转换为map集合
        pageInfo.setMapResult(ListUtil.getInstance());
        pageInfo.getResult().forEach((VO) -> {
            Map<String, Object> map = MapUtil.toMap(VO);
            map.put("抄表日期间隔类型", DateConstants.DateType.getValueByKey(VO.getIntervaltype()));
            pageInfo.getMapResult().add(map);
        });
        pageInfo.getResult().clear();
        PagingVO<Options> VO = new PagingVO<>();
        VO.setPagingModel(pageInfo);
        VO.setHeader(Header.getInstance().buildOptions(pageInfo.getParam().getOptionstype()));
        return VO;
    }

    @Override
    public Options getModelByGuid(Long guid) {
        return dao.selectByPrimaryKey(guid);
    }

    @Override
    public boolean checkUnique(Options model) {
        OptionsExample example = dao.getExample();
        OptionsExample.Criteria criteria = example.createCriteria();
        criteria.andGradeEqualTo(model.getGrade());
        criteria.andOptionstypeEqualTo(model.getOptionstype());
        return dao.checkUnique(example, "guid", model.getGuid());
    }

    @Override
    public List<OptionsVO> findOptionsByType(Class<?> cls) {
        OptionsExample example = dao.getExample();
        example.createCriteria().andOptionstypeEqualTo(cls.getSimpleName());
        List<Options> list = dao.selectByExample(example);
        ListUtil.sort(list, "grade", true);
        List<OptionsVO> result = ListUtil.getInstance();
        for (Options options : list) {
            OptionsVO VO = new OptionsVO();
            BeanUtil.copyPropertiesNotEmpty(VO, options);
            result.add(VO);
        }
        return result;
    }
}
