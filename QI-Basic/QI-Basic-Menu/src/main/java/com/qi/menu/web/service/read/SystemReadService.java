package com.qi.menu.web.service.read;

import com.qi.common.basic.service.BasicReadService;
import com.qi.menu.model.domain.BasicSystem;
import com.qi.menu.web.model.vo.SystemVO;

import java.util.List;

/**
 * Class PortalReadService
 *
 * @author 张麒 2016/9/2.
 * @version Description:
 */
public interface SystemReadService extends BasicReadService<BasicSystem> {

    List<BasicSystem> findNormalPortal();

    BasicSystem getBasicSystemByCode(String code);

    SystemVO getSystemVOByContextPath(String contextPath);
}
