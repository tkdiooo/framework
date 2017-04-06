package com.qi.assistant.web.service.read.impl;

import com.qi.assistant.web.dao.ReportDAO;
import com.qi.assistant.web.model.vo.BarVO;
import com.qi.assistant.web.model.vo.PieVO;
import com.qi.assistant.web.service.read.ReportReadService;
import com.qi.common.database.dao.DBType;
import com.qi.common.database.datasource.dynamic.ReadWriteDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class ReportReadServiceImpl
 *
 * @author 张麒 2016/9/9.
 * @version Description:
 */
@ReadWriteDataSource(DBType.READ)
@Service("ReportReadService")
public class ReportReadServiceImpl implements ReportReadService {

    @Autowired
    private ReportDAO dao;

    @Override
    public List<String> findYear() {
        return dao.findYear();
    }

    @Override
    public List<PieVO> findSpendingByPie(String year) {
        return dao.findSpendingByPie(year);
    }

    @Override
    public List<BarVO> findBeforeYearByBar(String year) {
        return dao.findBeforeYearByBar(Integer.valueOf(year));
    }

    @Override
    public List<BarVO> findFullYearByLine(String year) {
        return dao.findFullYearByLine(Integer.valueOf(year));
    }

    @Override
    public List<PieVO> findFullYearByLine(String year, Integer type) {
        return dao.findFullYearByLine(Integer.valueOf(year), type);
    }
}
