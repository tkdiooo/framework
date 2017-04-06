package com.qi.menu.rpc.hessian.consumer;

import com.qi.common.facade.annotation.HessianNamespace;
import com.qi.common.facade.hessian.BaseClient;
import com.qi.menu.model.dto.SystemDto;
import com.qi.menu.rpc.inf.RegisterAPI;

/**
 * Class RegisterConsumer
 *
 * @author 张麒 2016/9/3.
 * @version Description:
 */
@HessianNamespace("RegisterAPIService")
public class RegisterConsumer extends BaseClient<RegisterAPI> implements RegisterAPI {

    @Override
    public void register(SystemDto dto) {
        getClient().register(dto);
    }

    @Override
    public void updateStatus(String code, Integer status) {
        getClient().updateStatus(code, status);
    }
}
