package com.qi.menu.rpc.hessian.consumer;

import com.qi.common.facade.annotation.HessianNamespace;
import com.qi.common.facade.hessian.BaseClient;
import com.qi.common.model.result.ActionResult;
import com.qi.common.util.RpcUtil;
import com.qi.menu.model.dto.SystemDto;
import com.qi.menu.rpc.inf.PortalAPI;

/**
 * Class PortalConsumer
 *
 * @author 张麒 2016/9/1.
 * @version Description:
 */
@HessianNamespace("PortalAPIService")
public class PortalConsumer extends BaseClient<PortalAPI> implements PortalAPI {

    @Override
    public ActionResult<SystemDto> findValidPortal() {
        return RpcUtil.getActionResult(super.getClient().findValidPortal());
    }

    @Override
    public ActionResult<SystemDto> getPortalByCode(String code) {
        return RpcUtil.getActionResult(super.getClient().getPortalByCode(code));
    }

}
