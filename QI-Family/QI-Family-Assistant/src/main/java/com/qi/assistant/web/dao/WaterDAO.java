package com.qi.assistant.web.dao;

import com.qi.assistant.web.model.example.WaterExample;
import com.qi.assistant.web.model.model.Water;
import com.qi.common.dao.ibatis.inf.IBatisDao;

import java.util.List;

/**
 * Class WaterDAO
 *
 * @author 张麒 2016/8/30.
 * @version Description:
 */
public interface WaterDAO extends IBatisDao<Water, Long, WaterExample> {

    Integer countByQuantity(Long guid);

    Long getLatestReset();

    /**
     * 获取最新的费用单
     *
     * @return
     */
    Water getLatestModel();


    List<String> findYear();
}
