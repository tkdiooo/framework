package com.qi.assistant.web.service.transactional;

import com.qi.assistant.web.model.vo.WaterVO;

/**
 * Class WaterTransactionalService
 *
 * @author 张麒 2016/8/30.
 * @version Description:
 */
public interface WaterTransactionalService {

    void save(WaterVO condition);

    void delete(Long guid);
}
