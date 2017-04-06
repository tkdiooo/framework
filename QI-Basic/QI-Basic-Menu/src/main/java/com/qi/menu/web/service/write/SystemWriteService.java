package com.qi.menu.web.service.write;


import com.qi.menu.model.domain.BasicSystem;

/**
 * Class PortalWriteService
 *
 * @author 张麒 2016/9/2.
 * @version Description:
 */
public interface SystemWriteService {

    void register(BasicSystem condition);

    void updateStatus(String code, Integer status);
}
