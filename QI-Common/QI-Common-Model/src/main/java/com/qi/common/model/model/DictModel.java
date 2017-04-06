package com.qi.common.model.model;

import java.io.Serializable;

/**
 * Class DictModel
 *
 * @author 张麒 2016/5/12.
 * @version Description:
 */
public class DictModel implements Serializable {

    private static final long serialVersionUID = 6231290164795695534L;

    private String groupCode;
    private String id;
    private String itemCode;
    private String itemName;
    private String itemOrderIndex;
    private String parentCode;

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemOrderIndex() {
        return itemOrderIndex;
    }

    public void setItemOrderIndex(String itemOrderIndex) {
        this.itemOrderIndex = itemOrderIndex;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}
