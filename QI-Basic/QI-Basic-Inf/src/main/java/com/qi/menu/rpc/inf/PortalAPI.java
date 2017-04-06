package com.qi.menu.rpc.inf;

import com.qi.common.model.result.ActionResult;
import com.qi.menu.model.dto.SystemDto;

/**
 * Class PortalAPI
 *
 * @author 张麒 2016/9/1.
 * @version Description:
 */
public interface PortalAPI {

    ActionResult<SystemDto> findValidPortal();

    ActionResult<SystemDto> getPortalByCode(String code);
}
