package com.qi.assistant.web.service.read;

import com.qi.assistant.web.model.model.Options;
import com.qi.assistant.web.model.vo.OptionsVO;
import com.qi.common.basic.service.BasicReadService;

import java.util.List;

/**
 * Class OptionsReadService
 *
 * @author 张麒 2016/7/25.
 * @version Description:
 */
public interface OptionsReadService extends BasicReadService<Options> {

    List<OptionsVO> findOptionsByType(Class<?> cls);
}
