package com.qi.common.database.dao.impl;

import com.qi.common.cache.inf.ICacheControllerService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Class SqlMapClientTemplateCacheMonitor
 *
 * @author 张麒 2016/5/13.
 * @version Description:
 */
public class SqlMapClientTemplateCacheMonitor extends SqlMapClientTemplateMonitor {


    public ICacheControllerService getCacheClient() {
        return cacheClient;
    }

    protected void setCacheClient(ICacheControllerService cacheClient) {
        this.cacheClient = cacheClient;
    }


    private ICacheControllerService cacheClient;

    public List queryCacheForList(String statementName, int skipResults, int maxResults, String key, Date date) {
        // TODO Auto-generated method stub
        List list = (List) cacheClient.getCacheClient().get(key);

        if (list == null) {

            list = super.queryForList(statementName, skipResults, maxResults);

            if (list != null && list.size() > 0) {

                cacheClient.getCacheClient().put(key, list, date);
            }


        }

        return list;
    }

    public List queryCacheForList(String statementName, String key, Date date) {
        // TODO Auto-generated method stub
        List list = (List) cacheClient.getCacheClient().get(key);

        if (list == null) {

            list = super.queryForList(statementName);

            if (list != null && list.size() > 0) {

                cacheClient.getCacheClient().put(key, list, date);
            }

        }

        return list;
    }

    public List queryCacheForList(String statementName, Object parameterObject, String key, Date date) {

        List list = (List) cacheClient.getCacheClient().get(key);

        if (list == null) {

            list = super.queryForList(statementName, parameterObject);

            if (list != null && list.size() > 0) {

                cacheClient.getCacheClient().put(key, list, date);
            }

        }

        return list;

    }

    public List queryCacheForList(String statementName, Object parameterObject, int skipResults, int maxResults, String key, Date date) {
        // TODO Auto-generated method stub
        List list = (List) cacheClient.getCacheClient().get(key);

        if (list == null) {

            list = super.queryForList(statementName, parameterObject,
                    skipResults, maxResults);

            if (list != null && list.size() > 0) {

                cacheClient.getCacheClient().put(key, list, date);

            }

        }

        return list;
    }

    public Map queryCacheForMap(String statementName, Object parameterObject, String keyProperty, String valueProperty, String key, Date date) {
        // TODO Auto-generated method stub
        Map map = (Map) cacheClient.getCacheClient().get(key);

        if (map == null) {

            map = super.queryForMap(statementName, parameterObject,
                    keyProperty, valueProperty);

            if (map != null) {

                cacheClient.getCacheClient().put(key, map, date);

            }

        }

        return map;
    }

    public Map queryCacheForMap(String statementName, Object parameterObject, String keyProperty, String key, Date date) {
        // TODO Auto-generated method stub
        Map map = (Map) cacheClient.getCacheClient().get(key);

        if (map == null) {

            map = super
                    .queryForMap(statementName, parameterObject, keyProperty);

            if (map != null) {

                cacheClient.getCacheClient().put(key, map, date);
            }

        }

        return map;
    }

    public Object queryCacheForObject(String statementName, Object parameterObject, Object resultObject, String key, Date date) {
        // TODO Auto-generated method stub
        Object ob = cacheClient.getCacheClient().get(key);

        if (ob == null) {

            ob = super.queryForObject(statementName, parameterObject,
                    resultObject);

            if (ob != null) {

                cacheClient.getCacheClient().put(key, ob, date);

            }

        }

        return ob;
    }

    public Object queryCacheForObject(String statementName, Object parameterObject, String key, Date date) {
        // TODO Auto-generated method stub
        Object ob = cacheClient.getCacheClient().get(key);

        if (ob == null) {

            ob = super.queryForObject(statementName, parameterObject);

            if (ob != null) {

                cacheClient.getCacheClient().put(key, ob, date);
            }

        }

        return ob;

    }

    public Object queryCacheForObject(String statementName, String key, Date date) {
        // TODO Auto-generated method stub
        Object ob = cacheClient.getCacheClient().get(key);

        if (ob == null) {

            ob = super.queryForObject(statementName);

            if (ob != null) {

                cacheClient.getCacheClient().put(key, ob, date);
            }

        }

        return ob;

    }

    //----------------

    public List queryCacheForList(String statementName, int skipResults, int maxResults, String key, Integer date) {
        // TODO Auto-generated method stub
        List list = (List) cacheClient.getCacheClient().get(key);

        if (list == null) {

            list = super.queryForList(statementName, skipResults, maxResults);

            if (list != null && list.size() > 0) {

                cacheClient.getCacheClient().putTimeOut(key, list, date);
            }

        }

        return list;
    }

    public List queryCacheForList(String statementName, String key, Integer date) {
        // TODO Auto-generated method stub
        List list = (List) cacheClient.getCacheClient().get(key);

        if (list == null) {

            list = super.queryForList(statementName);

            if (list != null && list.size() > 0) {

                cacheClient.getCacheClient().putTimeOut(key, list, date);
            }
        }

        return list;
    }

    public List queryCacheForList(String statementName, Object parameterObject, String key, Integer date) {

        List list = (List) cacheClient.getCacheClient().get(key);

        if (list == null) {

            list = super.queryForList(statementName, parameterObject);

            if (list != null && list.size() > 0) {

                cacheClient.getCacheClient().putTimeOut(key, list, date);
            }

            return list;
        }

        return list;

    }

    public List queryCacheForList(String statementName, Object parameterObject, int skipResults, int maxResults, String key, Integer date) {
        // TODO Auto-generated method stub
        List list = (List) cacheClient.getCacheClient().get(key);

        if (list == null) {

            list = super.queryForList(statementName, parameterObject,
                    skipResults, maxResults);

            if (list != null && list.size() > 0) {

                cacheClient.getCacheClient().putTimeOut(key, list, date);
            }

        }

        return list;
    }

    public Map queryCacheForMap(String statementName, Object parameterObject, String keyProperty, String valueProperty, String key, Integer date) {
        // TODO Auto-generated method stub
        Map map = (Map) cacheClient.getCacheClient().get(key);

        if (map == null) {

            map = super.queryForMap(statementName, parameterObject,
                    keyProperty, valueProperty);

            if (map != null) {

                cacheClient.getCacheClient().putTimeOut(key, map, date);
            }

        }

        return map;
    }

    public Map queryCacheForMap(String statementName, Object parameterObject, String keyProperty, String key, Integer date) {
        // TODO Auto-generated method stub
        Map map = (Map) cacheClient.getCacheClient().get(key);

        if (map == null) {

            map = super
                    .queryForMap(statementName, parameterObject, keyProperty);

            if (map != null) {

                cacheClient.getCacheClient().putTimeOut(key, map, date);
            }

        }

        return map;
    }

    public Object queryCacheForObject(String statementName, Object parameterObject, Object resultObject, String key, Integer date) {
        // TODO Auto-generated method stub
        Object ob = cacheClient.getCacheClient().get(key);

        if (ob == null) {

            ob = super.queryForObject(statementName, parameterObject,
                    resultObject);

            if (ob != null) {

                cacheClient.getCacheClient().putTimeOut(key, ob, date);
            }

        }

        return ob;
    }

    public Object queryCacheForObject(String statementName, Object parameterObject, String key, Integer date) {
        // TODO Auto-generated method stub
        Object ob = cacheClient.getCacheClient().get(key);

        if (ob == null) {

            ob = super.queryForObject(statementName, parameterObject);

            if (ob != null) {

                cacheClient.getCacheClient().putTimeOut(key, ob, date);
            }

        }

        return ob;

    }

    public Object queryCacheForObject(String statementName, String key, Integer date) {
        Object ob = cacheClient.getCacheClient().get(key);

        if (ob == null) {

            ob = super.queryForObject(statementName);

            if (ob != null) {

                cacheClient.getCacheClient().putTimeOut(key, ob, date);
            }

        }

        return ob;

    }
}
