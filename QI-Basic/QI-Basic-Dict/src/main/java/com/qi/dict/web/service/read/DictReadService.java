package com.qi.dict.web.service.read;

import com.qi.common.basic.service.BasicReadService;
import com.qi.common.model.vo.tree.ZTreeVO;
import com.qi.dict.web.model.model.Dictionary;

import java.util.List;

/**
 * Class DictReadService
 *
 * @author 张麒 2016/7/25.
 * @version Description:
 */
public interface DictReadService extends BasicReadService<Dictionary> {


    /**
     * 根据Code获取字典实体
     *
     * @param code Code
     * @return Dictionary
     */
    Dictionary getDictionaryByCode(String code);

    /**
     * 根据ParentCode获取ZTree
     *
     * @param parentCode ParentCode
     * @return List<ZTreeVO>
     */
    List<ZTreeVO> findDictForZTree(String parentCode);

    /**
     * 根据ParentCode获取字典集合
     *
     * @param parentCode ParentCode
     * @return Dictionary
     */
    List<Dictionary> findDictByParent(String parentCode);
}
