package com.qi.common.database.dto;

import java.util.Date;

/**
 * Class BaseDtoImpl
 *
 * @author 张麒 2016/5/13.
 * @version Description:
 */
public abstract class BaseDtoImpl implements IBaseDto {

    private static final long serialVersionUID = 3046870768168089022L;

    private Long creator;

    private Date createtime;

    private Long updater;

    private Date updatetime;

    private Boolean isdelete;

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Long getUpdater() {
        return updater;
    }

    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Boolean getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Boolean isdelete) {
        this.isdelete = isdelete;
    }
}
