package com.qi.assistant.web.service.read;

import com.qi.assistant.web.model.model.Electric;
import com.qi.assistant.web.model.model.ElectricDetails;
import com.qi.assistant.web.model.vo.OptionsVO;
import com.qi.common.basic.service.BasicReadService;
import com.qi.common.model.vo.select.SelectVO;

import java.util.List;

/**
 * Class ElectricReadService
 *
 * @author 张麒 2016/8/11.
 * @version Description:
 */
public interface ElectricReadService extends BasicReadService<Electric> {

    List<SelectVO> findYear();

    List<ElectricDetails> findElectricDetailsByFKGuid(Long guid);

    OptionsVO getOptions(OptionsVO options, Electric electric);
}
