package com.qi.common.dao.ibatis.abs;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.qi.common.cache.inf.ICacheControllerService;
import com.qi.common.cache.memcached.MemcachedController;
import com.qi.common.database.dao.BaseDao;
import com.qi.common.database.dao.impl.BaseMySqlDaoImpl;
import com.qi.common.database.dto.IBaseDto;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * Class CommonMySqlDaoImpl
 *
 * @author 张麒 2016/5/13.
 * @version Description:
 */
public abstract class CommonMySqlDaoImpl<T extends IBaseDto, PK extends Serializable> extends BaseMySqlDaoImpl implements BaseDao {

    @Autowired
    private MemcachedController memcachedController;

    @Resource(name = "sqlMapClientMysql")
    private SqlMapClient sqlMapClientMysql;


    public SqlMapClient initSqlMapClient(){
        return sqlMapClientMysql;
    }


    public ICacheControllerService initCacheClient() {
        return memcachedController;
    }
}
