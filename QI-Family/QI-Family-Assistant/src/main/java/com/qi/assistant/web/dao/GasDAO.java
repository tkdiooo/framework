package com.qi.assistant.web.dao;

import com.qi.assistant.web.model.example.GasExample;
import com.qi.assistant.web.model.model.Gas;
import com.qi.common.dao.ibatis.inf.IBatisDao;

import java.util.List;

/**
 * Class GasDAO
 *
 * @author 张麒 2016/7/28.
 * @version Description:
 */
public interface GasDAO extends IBatisDao<Gas, Long, GasExample> {

    Integer countByGasQuantity(Long guid);

    Long getLatestReset();

    /**
     * 获取最新的费用单
     *
     * @return
     */
    Gas getLatestModel();


    List<String> findYear();
}
