package com.qi.menu.web.service.transactional;


import com.qi.menu.model.domain.BasicMenu;

/**
 * Class MenuTransactionalService
 *
 * @author 张麒 2016/7/15.
 * @version Description:
 */
public interface MenuTransactionalService {

    void saveMenu(BasicMenu condition);
}
