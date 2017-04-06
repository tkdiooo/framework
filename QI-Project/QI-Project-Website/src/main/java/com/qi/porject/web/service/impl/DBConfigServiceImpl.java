package com.qi.porject.web.service.impl;

import com.qi.common.model.model.PagingModel;
import com.qi.common.model.vo.paging.PagingVO;
import com.qi.porject.web.service.DBConfigService;
import com.qi.porject.web.tool.Header;
import org.springframework.stereotype.Component;

/**
 * Class DBConfigServiceImpl
 *
 * @author 张麒 2017/2/14.
 * @version Description:
 */
@Component
public class DBConfigServiceImpl implements DBConfigService {

    //    @Async
    @Override
    public PagingVO queryPagination(PagingModel pageInfo) {
        PagingVO VO = new PagingVO();
        VO.setPagingModel(pageInfo);
        VO.setHeader(Header.getInstance().buildHeader(0));
        return VO;
    }

    @Override
    public Object getModelByGuid(Long guid) {
        return null;
    }

    @Override
    public boolean checkUnique(Object model) {
        return false;
    }
}
