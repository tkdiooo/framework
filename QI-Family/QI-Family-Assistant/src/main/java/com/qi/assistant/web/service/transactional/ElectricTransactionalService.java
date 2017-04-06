package com.qi.assistant.web.service.transactional;

import com.qi.assistant.web.model.vo.ElectricVO;

/**
 * Class ElectricTransactionalService
 *
 * @author 张麒 2016/8/25.
 * @version Description:
 */
public interface ElectricTransactionalService {

    void save(ElectricVO condition);

    void delete(Long guid);
}
