package com.qi.assistant.web.service.transactional;

import com.qi.assistant.web.model.vo.GasVO;

/**
 * Class GasTransactionalService
 *
 * @author 张麒 2016/8/3.
 * @version Description:
 */
public interface GasTransactionalService {

    void save(GasVO condition);

    void delete(Long guid);
}
