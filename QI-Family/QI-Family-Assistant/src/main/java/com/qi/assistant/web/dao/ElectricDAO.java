package com.qi.assistant.web.dao;

import com.qi.assistant.web.model.example.ElectricExample;
import com.qi.assistant.web.model.model.Electric;
import com.qi.common.dao.ibatis.inf.IBatisDao;

import java.util.List;

/**
 * Class ElectricDAO
 *
 * @author 张麒 2016/8/11.
 * @version Description:
 */
public interface ElectricDAO extends IBatisDao<Electric, Long, ElectricExample> {

    Integer countByQuantity(Long guid);

    Long getLatestReset();

    /**
     * 获取最新的费用单
     *
     * @return
     */
    Electric getLatestModel();


    List<String> findYear();
}
