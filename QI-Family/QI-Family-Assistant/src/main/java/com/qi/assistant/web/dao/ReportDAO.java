package com.qi.assistant.web.dao;

import com.qi.assistant.web.model.vo.BarVO;
import com.qi.assistant.web.model.vo.PieVO;
import com.qi.common.dao.ibatis.inf.IBatisDao;

import java.util.List;

/**
 * Class ReportDAO
 *
 * @author 张麒 2016/9/8.
 * @version Description:
 */
public interface ReportDAO extends IBatisDao {

    List<String> findYear();

    List<PieVO> findSpendingByPie(String year);

    List<BarVO> findBeforeYearByBar(Integer year);

    List<BarVO> findFullYearByLine(Integer year);

    List<PieVO> findFullYearByLine(Integer year, Integer type);
}
