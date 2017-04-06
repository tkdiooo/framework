package com.qi.menu.rpc.hessian.provider;

import com.qi.common.constants.RpcConstants;
import com.qi.common.model.result.ActionResult;
import com.qi.common.util.RpcUtil;
import com.qi.common.util.ThrowableUtil;
import com.qi.menu.model.dto.SystemDto;
import com.qi.menu.rpc.cache.PortalCacheFactory;
import com.qi.menu.rpc.inf.PortalAPI;

import javax.jws.soap.SOAPBinding;
import java.util.Map;

/**
 * Class PortalProvider
 *
 * @author 张麒 2016/9/1.
 * @version Description:
 */
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class PortalProvider implements PortalAPI {

    @Override
    public ActionResult<SystemDto> findValidPortal() {
        ActionResult<SystemDto> ar;
        try {
            Map<String, SystemDto> dataMap = PortalCacheFactory.getPortalDtoCache();
            ar = RpcUtil.sendActionResult(RpcConstants.SUCCESSFUL, RpcConstants.ReplyCode.Code200);
//            ar.setDataMap(dataMap);
        } catch (Exception e) {
            ar = RpcUtil.sendActionResult(RpcConstants.FAILURE, RpcConstants.ReplyCode.Code500, ThrowableUtil.getRootMessage(e));
        }
        return ar;
    }

    @Override
    public ActionResult<SystemDto> getPortalByCode(String code) {
        ActionResult<SystemDto> ar;
        try {
            ar = RpcUtil.sendActionResult(RpcConstants.SUCCESSFUL, RpcConstants.ReplyCode.Code200);
            ar.setResult(PortalCacheFactory.getPortalDtoCacheByCode(code));
        } catch (Exception e) {
            ar = RpcUtil.sendActionResult(RpcConstants.FAILURE, RpcConstants.ReplyCode.Code500, ThrowableUtil.getRootMessage(e));
        }
        return ar;
    }
}
