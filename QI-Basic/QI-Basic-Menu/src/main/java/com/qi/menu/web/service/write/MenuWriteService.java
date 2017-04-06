package com.qi.menu.web.service.write;

/**
 * Class MenuWriteService
 *
 * @author 张麒 2016/7/6.
 * @version Description:
 */
public interface MenuWriteService {

    void statusChanges(String guids, boolean status);

    void delete(Long guid);

    void batchDelete(String guids);

    void sortable(String sortable);
}
