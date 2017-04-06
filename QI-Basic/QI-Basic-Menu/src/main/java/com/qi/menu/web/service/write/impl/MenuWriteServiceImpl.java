package com.qi.menu.web.service.write.impl;

import com.qi.common.database.dao.DBType;
import com.qi.common.database.datasource.dynamic.ReadWriteDataSource;
import com.qi.common.tool.Logger;
import com.qi.menu.model.domain.BasicMenu;
import com.qi.menu.web.dao.BasicMenuDAO;
import com.qi.menu.web.service.write.MenuWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class MenuWriteServiceImpl
 *
 * @author 张麒 2016/7/6.
 * @version Description:
 */
@ReadWriteDataSource(DBType.WRITE)
@Service("MenuWriteService")
public class MenuWriteServiceImpl implements MenuWriteService {

    private static final Logger logger = new Logger(MenuWriteServiceImpl.class);

    @Autowired
    private BasicMenuDAO menuDAO;

    @Override
    public void statusChanges(String guids, boolean status) {
        BasicMenu menu;
        for (String guid : guids.split(",")) {
            menu = new BasicMenu();
            menu.setGuid(Long.valueOf(guid));
            menu.setStatus(status);
            menuDAO.updateByPrimaryKeySelective(menu);
        }
    }

    @Override
    public void delete(Long guid) {
        menuDAO.deleteByPrimaryKey(guid);
    }

    @Override
    public void batchDelete(String guids) {
        for (String guid : guids.split(",")) {
            menuDAO.deleteByPrimaryKey(Long.valueOf(guid));
        }
    }

    @Override
    public void sortable(String sortable) {
        BasicMenu menu;
        for (String sort : sortable.split("#")) {
            menu = new BasicMenu();
            menu.setGuid(Long.valueOf(sort.split(",")[0]));
            menu.setSort(Integer.valueOf(sort.split(",")[1]));
            menuDAO.updateByPrimaryKeySelective(menu);
        }
    }
}
