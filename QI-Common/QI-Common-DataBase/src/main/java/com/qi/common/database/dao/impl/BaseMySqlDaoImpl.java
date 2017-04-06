package com.qi.common.database.dao.impl;

import com.qi.common.database.dao.BaseDao;
import com.qi.common.database.dto.PageDto;
import com.qi.common.database.dto.Pagination;
import com.qi.common.util.MapUtil;
import com.qi.common.util.StringUtil;

import java.util.List;
import java.util.Map;

/**
 * Class BaseMySqlDaoImpl
 *
 * @author 张麒 2016/5/13.
 * @version Description:
 */
public abstract class BaseMySqlDaoImpl extends BaseDaoImpl implements BaseDao {

//	public BaseMySqlDaoImpl(Class<T> persistentClass) {
//		super(persistentClass);
//	}

//	@Resource(name = "dynamicMySql")
//	private IDynamicSqlService dynamicMySql;

//	@PostConstruct
//	public void InitMySqlDaoImpl() {
//
//		super.setDynamicService(dynamicMySql);
//
//	}


    @Override
    @SuppressWarnings("unchecked")
    public Pagination findObjectsCacheWithPg(String statementName, PageDto pageDto, String key, Integer time) {

        Pagination ob = (Pagination) getSqlMapClientTemplate().getCacheClient().getCacheClient().get(key);

        if (ob == null) {

            Map map = MapUtil.toMap(pageDto.getDto());

            Object count = getSqlMapClientTemplate().queryForObject(statementName, map);

            int totalCount = Integer.parseInt(count.toString());

            pageDto.setTotalCount(totalCount);

            pageDto.calStart();

            map.put("start", pageDto.getStart());

            map.put("limit", pageDto.getLimit());

            List rstList = getSqlMapClientTemplate().queryForList(statementName, map);

            Pagination pg = new Pagination();

            copyProperties(pg, pageDto);

            pg.setTotalCount(totalCount);

            pg.setResultList(rstList);

            getSqlMapClientTemplate().getCacheClient().getCacheClient().putTimeOut(key, pg, time);

            return pg;

        }

        return ob;

    }

    @Override
    @SuppressWarnings("unchecked")
    public Pagination findObjectsWithPg(String statementName, PageDto pageDto) {
        if (StringUtil.isBlank(statementName)) {
            return null;
        }

        Map map = MapUtil.toMap(pageDto.getDto());

        String mapingId = statementName.split("[.]")[0];
        String statementId = statementName.split("[.]")[1];
        String COUNT_PRE = "count";
        String countStatument = COUNT_PRE + statementId.substring(0, 1).toUpperCase() + statementId.substring(1);

        Object count = getSqlMapClientTemplate().queryForObject(mapingId + "." + countStatument, map);

        int totalCount = Integer.parseInt(count.toString());

        pageDto.setTotalCount(totalCount);

        pageDto.calStart();

        map.put("start", pageDto.getStart());

        map.put("limit", pageDto.getLimit());

        List rstList = getSqlMapClientTemplate().queryForList(statementName, map);

        Pagination pg = new Pagination();

        copyProperties(pg, pageDto);

        pg.setTotalCount(totalCount);

        pg.setResultList(rstList);

        return pg;
    }
}
