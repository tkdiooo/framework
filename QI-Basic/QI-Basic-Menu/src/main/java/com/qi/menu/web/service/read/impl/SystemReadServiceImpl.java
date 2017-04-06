package com.qi.menu.web.service.read.impl;

import com.qi.common.database.dao.DBType;
import com.qi.common.database.datasource.dynamic.ReadWriteDataSource;
import com.qi.common.model.model.PagingModel;
import com.qi.common.model.vo.paging.OperateVO;
import com.qi.common.model.vo.paging.PagingVO;
import com.qi.common.model.vo.paging.TitleVO;
import com.qi.common.tool.Logger;
import com.qi.common.util.BeanUtil;
import com.qi.common.util.ListUtil;
import com.qi.common.util.MapUtil;
import com.qi.menu.model.domain.BasicSystem;
import com.qi.menu.rpc.constants.PortalConstants;
import com.qi.menu.web.cache.CacheFactory;
import com.qi.menu.web.dao.BasicSystemDAO;
import com.qi.menu.web.model.example.BasicSystemExample;
import com.qi.menu.web.model.vo.SystemVO;
import com.qi.menu.web.service.read.SystemReadService;
import com.qi.menu.web.tool.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Class PortalReadServiceImpl
 *
 * @author 张麒 2016/9/2.
 * @version Description:
 */
@ReadWriteDataSource(DBType.READ)
@Service("PortalReadService")
public class SystemReadServiceImpl implements SystemReadService {

    private static final Logger logger = new Logger(SystemReadServiceImpl.class);

    @Autowired
    private BasicSystemDAO dao;

    @Override
    public PagingVO queryPagination(PagingModel<BasicSystem> pageInfo) {
        // 分页查询
        dao.queryPagination(pageInfo);
        // List集合转换为map集合
        pageInfo.setMapResult(ListUtil.getInstance());
        pageInfo.getResult().forEach((VO) -> {
            Map<String, Object> map = MapUtil.toMap(VO);
            map.put("系统类型", PortalConstants.Type.getValueByKey(VO.getStatus()));
            map.put("状态", PortalConstants.Status.getValueByKey(VO.getStatus()));
            map.put(TitleVO.fieldType.operate.name(), new OperateVO(OperateVO.operateType.search));
            pageInfo.getMapResult().add(map);
        });
        pageInfo.getResult().clear();
        PagingVO<BasicSystem> VO = new PagingVO<>();
        VO.setPagingModel(pageInfo);
        VO.setHeader(Header.getInstance().buildHeader(1));
        return VO;
    }

    @Override
    public BasicSystem getModelByGuid(Long guid) {
        return dao.selectByPrimaryKey(guid);
    }

    @Override
    public boolean checkUnique(BasicSystem model) {
        BasicSystemExample example = dao.getExample();
        example.createCriteria().andCodeEqualTo(model.getCode());
        return dao.checkUnique(example, "guid", model.getGuid());
    }

    @Override
    public List<BasicSystem> findNormalPortal() {
        BasicSystemExample example = dao.getExample();
        example.createCriteria().andStatusEqualTo(PortalConstants.Status.Normal.getKey());
        example.setOrderByClause("Guid ASC");
        return dao.selectByExample(example);
    }

    @Override
    public BasicSystem getBasicSystemByCode(String code) {
        BasicSystemExample example = dao.getExample();
        example.createCriteria().andCodeEqualTo(code);
        return dao.getByExample(example);
    }

    @Override
    public SystemVO getSystemVOByContextPath(String contextPath) {
        BasicSystem model = ListUtil.getBeanByList("contextpath", "/" + contextPath, CacheFactory.getBasicSystemCache());
        return BeanUtil.copyPropertiesNotEmpty(SystemVO.class, model);
    }

}
