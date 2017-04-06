package com.qi.common.dao.model;

import com.qi.common.database.dto.BaseDtoImpl;
import com.qi.common.database.dto.IBaseDto;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Class BaseModel
 *
 * @author 张麒 2016/5/6.
 * @version Description:
 */
public abstract class BaseModel extends BaseDtoImpl {

    private static final long serialVersionUID = 6007104807658849273L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
