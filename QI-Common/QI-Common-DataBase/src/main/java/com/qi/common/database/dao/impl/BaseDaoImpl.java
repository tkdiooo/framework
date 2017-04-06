package com.qi.common.database.dao.impl;

import com.qi.common.database.dto.BaseListDto;
import com.qi.common.database.dto.IBaseDto;
import com.qi.common.database.dto.PageDto;
import com.qi.common.database.dto.Pagination;
import com.qi.common.tool.Logger;
import com.qi.common.util.BeanUtil;
import com.qi.common.util.MapUtil;

import java.util.List;
import java.util.Map;

/**
 * Class BaseDaoImpl
 *
 * @author 张麒 2016/5/13.
 * @version Description:
 */
public abstract class BaseDaoImpl extends SqlMapClientDaoSupportMonitor {
//    private ICacheControllerService cacheClient;
//
//	private SqlMapClient sqlMapClient;

//    @Autowired(required = false)
//    @Qualifier("dynamicDataSourceCommonUtil")
//	private DynamicDataSourceCommonUtil dynamicDataSourceCommonUtil;

    //protected IDynamicSqlService dynamicService;

//	public IDynamicSqlService getDynamicService() {
//		return dynamicService;
//	}
//
//	public void setDynamicService(IDynamicSqlService dynamicService) {
//		this.dynamicService = dynamicService;
//	}

//	@PostConstruct
//	public void initSqlMapClient() {
//		// super.setSqlMapClient(sqlMapClient);
//		super.setCacheClient(cacheClient);
//
//		//super.setDynamicDataSourceCommonUtil(dynamicDataSourceCommonUtil);
//	}

    protected final Logger logger = new Logger(this.getClass());

    //protected Class<T> persistentClass;

    //    /**
//     * @param persistentClass
//     */
//	public BaseDaoImpl(final Class<T> persistentClass) {
//		super();
//		//this.persistentClass = persistentClass;
//
//	}
    @SuppressWarnings("unchecked")
    public Pagination findObjectsWithPg(String statementName, PageDto pageDto) {

        Map map = MapUtil.toMap(pageDto.getDto());

        Object count = getSqlMapClientTemplate().queryForObject(statementName, map);

        int totalCount = Integer.parseInt(count.toString());

        pageDto.setTotalCount(totalCount);

        pageDto.calStart();

        map.put("start", pageDto.getStart());

        map.put("end", pageDto.getEnd());

        List rstList = getSqlMapClientTemplate().queryForList(statementName, map);

        Pagination pg = new Pagination();

        copyProperties(pg, pageDto);

        pg.setTotalCount(totalCount);

        pg.setResultList(rstList);

        return pg;
    }

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

            map.put("end", pageDto.getEnd());

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

    @Deprecated
    public Pagination findObjectsWithPg(String statementName, IBaseDto baseDto, BaseListDto pageDto) {

        Object count = getSqlMapClientTemplate().queryForObject(statementName, baseDto);

        int totalCount = Integer.parseInt(count.toString());

        pageDto.setTotalCount(totalCount);
        pageDto.calStart();
        // pageDto.setNeedCount(false);

        List rstList = getSqlMapClientTemplate().queryForList(statementName, baseDto, pageDto.getStart(), pageDto.getLimit());

        Pagination pg = new Pagination();

        copyProperties(pg, pageDto);

        pg.setTotalCount(totalCount);
        pg.setResultList(rstList);

        return pg;
    }

    protected void copyProperties(Pagination pg, BaseListDto pageDto) {
        try {
            BeanUtil.copyProperties(pg, pageDto);
        } catch (Exception e) {
            logger.error("", e);
            throw new RuntimeException();
        }
    }
}
