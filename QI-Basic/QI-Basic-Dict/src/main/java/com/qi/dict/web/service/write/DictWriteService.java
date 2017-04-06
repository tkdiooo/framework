package com.qi.dict.web.service.write;

/**
 * Class DictWriteService
 *
 * @author 张麒 2016/7/25.
 * @version Description:
 */
public interface DictWriteService {
    
    void statusChanges(String guids, boolean status);

    void delete(Long guid);

    void batchDelete(String guids);

    void sortable(String sortable);
}
