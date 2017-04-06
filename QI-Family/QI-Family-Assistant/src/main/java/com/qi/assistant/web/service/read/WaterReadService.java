package com.qi.assistant.web.service.read;

import com.qi.assistant.web.model.model.Water;
import com.qi.assistant.web.model.model.WaterDetails;
import com.qi.assistant.web.model.vo.OptionsVO;
import com.qi.common.basic.service.BasicReadService;
import com.qi.common.model.vo.select.SelectVO;

import java.util.List;

/**
 * Class WaterReadService
 *
 * @author 张麒 2016/8/30.
 * @version Description:
 */
public interface WaterReadService extends BasicReadService<Water> {


    List<SelectVO> findYear();

    List<WaterDetails> findWaterDetailsByFKGuid(Long guid);

    OptionsVO getOptions(OptionsVO options, Water water);
}
