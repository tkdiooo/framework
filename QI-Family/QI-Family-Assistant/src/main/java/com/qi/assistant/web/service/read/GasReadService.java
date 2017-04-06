package com.qi.assistant.web.service.read;

import com.qi.assistant.web.model.model.Gas;
import com.qi.assistant.web.model.model.GasDetails;
import com.qi.assistant.web.model.vo.OptionsVO;
import com.qi.common.basic.service.BasicReadService;
import com.qi.common.model.vo.select.SelectVO;

import java.util.List;

/**
 * Class OptionsReadService
 *
 * @author 张麒 2016/7/25.
 * @version Description:
 */
public interface GasReadService extends BasicReadService<Gas> {

    List<SelectVO> findYear();

    List<GasDetails> findGasDetailsByGasGuid(Long gasGuid);

    OptionsVO getOptions(OptionsVO options, Gas gas);
}
