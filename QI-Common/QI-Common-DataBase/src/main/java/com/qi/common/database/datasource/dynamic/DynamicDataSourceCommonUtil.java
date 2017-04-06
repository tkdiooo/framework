package com.qi.common.database.datasource.dynamic;

import com.qi.common.database.datasource.IPatitionAchieveService;
import com.qi.common.database.datasource.interceptor.ISMoreDBConfig;
import com.qi.common.database.dto.BaseDtoAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Class DynamicDataSourceCommonUtil
 *
 * @author 张麒 2016/5/13.
 * @version Description:
 */
public class DynamicDataSourceCommonUtil {


    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceCommonUtil.class);

    @Autowired(required = false)
    private IPatitionAchieveService patitionAchieve;

    public void setDataSource(Object parameterObject) {

        String dataSource = null;

        if ("true".equals(ISMoreDBConfig.getDBConfig())) {

            logger.info("ISMoreDBConfig is true");

            DbContextHolder.clearDbType();

            if (parameterObject instanceof BaseDtoAdapter) {

                BaseDtoAdapter vo = (BaseDtoAdapter) parameterObject;

                dataSource = patitionAchieve.getDataSource(vo.getPatitionLogo());

                if (dataSource != null) {

                    DbContextHolder.setDbType(dataSource);

                    return;
                }

            }

        }

    }

    public void setDataSource(String dataSource) {

        DbContextHolder.setDbType(dataSource);
    }

}
