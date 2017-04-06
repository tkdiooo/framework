package com.qi.menu.rpc.inf;


import com.qi.menu.model.dto.SystemDto;

/**
 * Class RegisterAPI
 *
 * @author 张麒 2016/9/3.
 * @version Description:
 */
public interface RegisterAPI {

    void register(SystemDto dto);

    void updateStatus(String code, Integer status);
}
