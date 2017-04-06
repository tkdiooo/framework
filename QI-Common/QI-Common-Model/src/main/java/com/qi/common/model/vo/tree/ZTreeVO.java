package com.qi.common.model.vo.tree;

/**
 * Class ZTreeVO
 *
 * @author 张麒 2016/7/18.
 * @version Description:
 */
public class ZTreeVO {

    private String id;
    private String pId;
    private String name;
    private boolean open = false;
    private boolean isParent;

    public ZTreeVO() {

    }

    public ZTreeVO(String id, String pId, String name, boolean isParent) {
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.isParent = isParent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPId() {
        return pId;
    }

    public void setPId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(boolean isParent) {
        this.isParent = isParent;
    }
}
