package com.qi.dict.web.service.transactional;

import com.qi.dict.web.model.model.Dictionary;

/**
 * Class DictTransactionalService
 *
 * @author 张麒 2016/7/25.
 * @version Description:
 */
public interface DictTransactionalService {

    void saveDict(Dictionary condition);
}
