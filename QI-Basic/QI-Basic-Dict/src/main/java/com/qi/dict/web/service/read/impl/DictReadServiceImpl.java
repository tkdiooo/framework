package com.qi.dict.web.service.read.impl;

import com.qi.common.constants.StatusConstants;
import com.qi.common.database.dao.DBType;
import com.qi.common.database.datasource.dynamic.ReadWriteDataSource;
import com.qi.common.model.model.PagingModel;
import com.qi.common.model.vo.paging.PagingVO;
import com.qi.common.model.vo.tree.ZTreeVO;
import com.qi.common.util.ListUtil;
import com.qi.common.util.MapUtil;
import com.qi.common.util.StringUtil;
import com.qi.common.util.TreeUtil;
import com.qi.dict.web.config.ApplicationConfig;
import com.qi.dict.web.dao.DictionaryDAO;
import com.qi.dict.web.model.example.DictionaryExample;
import com.qi.dict.web.model.model.Dictionary;
import com.qi.dict.web.service.read.DictReadService;
import com.qi.dict.web.tool.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class DictReadServiceImpl
 *
 * @author 张麒 2016/7/25.
 * @version Description:
 */
@ReadWriteDataSource(DBType.READ)
@Service("DictReadService")
public class DictReadServiceImpl implements DictReadService {

    @Autowired
    private DictionaryDAO dao;

    @Override
    public boolean checkUnique(Dictionary model) {
        DictionaryExample example = dao.getExample();
        example.createCriteria().andDictcodeEqualTo(model.getDictcode());
        return dao.checkUnique(example, "guid", model.getGuid());
    }

    @Override
    public PagingVO queryPagination(PagingModel<Dictionary> pageInfo) {
        // 分页查询
        dao.queryPagination(pageInfo);
        // List集合转换为map集合
        pageInfo.setMapResult(new ArrayList<>());
        pageInfo.getResult().forEach((VO) -> {
            Map<String, Object> map = MapUtil.toMap(VO);
            map.put("状态", StatusConstants.Status.getValueByKey(VO.getStatus()));
            pageInfo.getMapResult().add(map);
        });
        pageInfo.getResult().clear();
        PagingVO<Dictionary> VO = new PagingVO<>();
        VO.setPagingModel(pageInfo);
        VO.setHeader(Header.getInstance().buildHeader(0));
        return VO;
    }

    @Override
    public Dictionary getModelByGuid(Long guid) {
        return dao.selectByPrimaryKey(guid);
    }

    @Override
    public Dictionary getDictionaryByCode(String code) {
        DictionaryExample example = dao.getExample();
        example.createCriteria().andDictcodeEqualTo(code);
        List<Dictionary> list = dao.selectByExample(example);
        return list.size() == 1 ? list.get(0) : null;
    }

    @Override
    public List<ZTreeVO> findDictForZTree(String parentCode) {
        List<ZTreeVO> dataSet = new ArrayList<>();
        if (StringUtil.isBlank(parentCode)) {
            ZTreeVO VO = new ZTreeVO();
            VO.setId(ApplicationConfig.ROOT_CODE);
            VO.setName(ApplicationConfig.ROOT_NODE);
            VO.setPId("-1");
            VO.setIsParent(true);
            dataSet.add(VO);
        } else {
            List<Dictionary> list = findDictByParent(parentCode);
            list.forEach((menu) -> {
                menu.setStatus(StatusConstants.YesNo.No.getKey());
                dataSet.add(TreeUtil.convertZTree(menu, "dictcode", "parentcode", "dictname", "status"));
            });
        }
        return dataSet;
    }

    @Override
    public List<Dictionary> findDictByParent(String parentCode) {
        // 读取菜单数据
        DictionaryExample example = dao.getExample();
        example.createCriteria().andParentcodeEqualTo(parentCode);
        List<Dictionary> list = dao.selectByExample(example);
        ListUtil.sort(list, "sort", true);
        return list;
    }
}
