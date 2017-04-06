package com.qi.assistant.web.service.read;

import com.qi.assistant.web.model.vo.BarVO;
import com.qi.assistant.web.model.vo.PieVO;

import java.util.List;

/**
 * Class ReportReadService
 *
 * @author 张麒 2016/9/9.
 * @version Description:
 */
public interface ReportReadService {

    List<String> findYear();

    List<PieVO> findSpendingByPie(String year);

    List<BarVO> findBeforeYearByBar(String year);

    List<BarVO> findFullYearByLine(String year);

    List<PieVO> findFullYearByLine(String year, Integer type);
}
