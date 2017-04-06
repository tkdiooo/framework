package com.qi.menu.rpc.hessian.provider;

import com.qi.common.util.BeanUtil;
import com.qi.common.util.SpringContextUtil;
import com.qi.menu.model.dto.SystemDto;
import com.qi.menu.model.domain.BasicSystem;
import com.qi.menu.rpc.cache.PortalCacheFactory;
import com.qi.menu.rpc.inf.RegisterAPI;
import com.qi.menu.web.service.write.SystemWriteService;

import javax.jws.soap.SOAPBinding;

/**
 * Class RegisterProvider
 *
 * @author 张麒 2016/9/3.
 * @version Description:
 */
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class RegisterProvider implements RegisterAPI {

    private SystemWriteService service = (SystemWriteService) SpringContextUtil.getBean("PortalWriteService");

    @Override
    public void register(SystemDto dto) {
        try {
            BasicSystem portal = BeanUtil.copyPropertiesNotEmpty(BasicSystem.class, dto);
            service.register(portal);
            PortalCacheFactory.refreshPortalDtoCache();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStatus(String code, Integer status) {
        try {
            service.updateStatus(code, status);
            PortalCacheFactory.refreshPortalDtoCache();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
