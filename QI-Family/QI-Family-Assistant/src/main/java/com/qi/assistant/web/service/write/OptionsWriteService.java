package com.qi.assistant.web.service.write;

/**
 * Class OptionsWriteService
 *
 * @author 张麒 2016/7/25.
 * @version Description:
 */
public interface OptionsWriteService {
    
    void statusChanges(String guids, boolean status);

    void delete(Long guid);

    void batchDelete(String guids);

    void sortable(String sortable);
}
