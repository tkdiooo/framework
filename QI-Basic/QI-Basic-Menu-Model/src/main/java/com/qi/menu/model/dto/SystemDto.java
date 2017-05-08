package com.qi.menu.model.dto;

import com.qi.common.constants.LabelConstants;
import com.qi.menu.model.constants.CommonConstants;
import com.qi.menu.model.domain.BasicSystem;

import java.io.Serializable;

/**
 * Class PortalDto
 *
 * @author 张麒 2016/9/1.
 * @version Description:
 */
public class SystemDto extends BasicSystem implements Serializable {

    private static final long serialVersionUID = 3804734236448719618L;

    public String getPortalUrl() {
        return this.getDomain() + CommonConstants.PLATFORM_CONTEXT_PATH + CommonConstants.LAYOUT_TSHAPE + this.getContextpath() + LabelConstants.PERIOD + LabelConstants.HTML;
    }

    @Override
    public String toString() {
        if (null != getGuid())
            return getGuid().toString();
        return "";
    }

}
