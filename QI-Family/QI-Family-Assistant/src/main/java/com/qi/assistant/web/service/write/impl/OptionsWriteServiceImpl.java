package com.qi.assistant.web.service.write.impl;

import com.qi.assistant.web.service.write.OptionsWriteService;
import com.qi.common.database.dao.DBType;
import com.qi.common.database.datasource.dynamic.ReadWriteDataSource;
import org.springframework.stereotype.Service;

/**
 * Class OptionsWriteServiceImpl
 *
 * @author 张麒 2016/7/25.
 * @version Description:
 */
@ReadWriteDataSource(DBType.WRITE)
@Service("DictWriteService")
public class OptionsWriteServiceImpl implements OptionsWriteService {

//    @Autowired
//    private OptionsDAO dictDAO;

    @Override
    public void statusChanges(String guids, boolean status) {
//        Dictionary dict;
//        for (String guid : guids.split(",")) {
//            dict = new Dictionary();
//            dict.setGuid(Long.valueOf(guid));
//            dict.setStatus(status);
//            dictDAO.updateByPrimaryKeySelective(dict);
//        }
    }

    @Override
    public void delete(Long guid) {
//        dictDAO.deleteByPrimaryKey(guid);
    }

    @Override
    public void batchDelete(String guids) {
//        for (String guid : guids.split(",")) {
//            dictDAO.deleteByPrimaryKey(Long.valueOf(guid));
//        }
    }

    @Override
    public void sortable(String sortable) {
//        Dictionary dict;
//        for (String sort : sortable.split("#")) {
//            dict = new Dictionary();
//            dict.setGuid(Long.valueOf(sort.split(",")[0]));
//            dict.setSort(Integer.valueOf(sort.split(",")[1]));
//            dictDAO.updateByPrimaryKeySelective(dict);
//        }
    }
}
