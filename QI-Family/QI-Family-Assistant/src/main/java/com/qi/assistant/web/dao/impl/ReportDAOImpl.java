package com.qi.assistant.web.dao.impl;

import com.qi.assistant.web.dao.ReportDAO;
import com.qi.assistant.web.model.vo.BarVO;
import com.qi.assistant.web.model.vo.PieVO;
import com.qi.common.dao.annotation.DaoSqlMapNamespace;
import com.qi.common.dao.ibatis.abs.AbstractSqlDaoImpl;
import com.qi.common.util.MapUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Class ReportDAOImpl
 *
 * @author 张麒 2016/9/8.
 * @version Description:
 */
@Component("ReportDAO")
@DaoSqlMapNamespace("t_family_report")
public class ReportDAOImpl extends AbstractSqlDaoImpl implements ReportDAO {

    public ReportDAOImpl() {
        super(ReportDAOImpl.class);
    }

    @Override
    public Object getExample() {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> findYear() {
        return queryForList("findYear", null, String.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PieVO> findSpendingByPie(String year) {
        Map<String, String> params = MapUtil.getInstance();
        params.put("year", year);
        return queryForList("querySpendingByPie", params, PieVO.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<BarVO> findBeforeYearByBar(Integer year) {
        Map<String, Integer> params = MapUtil.getInstance();
        params.put("year", year);
        params.put("before", (year - 1));
        return queryForList("queryBeforeYearByBar", params, BarVO.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<BarVO> findFullYearByLine(Integer year) {
        Map<String, Integer> params = MapUtil.getInstance();
        params.put("year", year);
        return queryForList("queryFullYearByLine", params, BarVO.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PieVO> findFullYearByLine(Integer year, Integer type) {
        Map<String, Integer> params = MapUtil.getInstance();
        params.put("year", year);
        switch (type) {
            case 0:
                return queryForList("queryFullYearWaterByLine", params, PieVO.class);
            case 1:
                return queryForList("queryFullYearElectricByLine", params, PieVO.class);
            case 2:
                return queryForList("queryFullYearGasByLine", params, PieVO.class);
        }
        return null;
    }
}
