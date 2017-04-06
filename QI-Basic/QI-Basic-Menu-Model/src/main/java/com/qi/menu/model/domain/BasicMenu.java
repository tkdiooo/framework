package com.qi.menu.model.domain;

import com.qi.common.dao.model.BaseModel;

public class BasicMenu extends BaseModel {

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_basic_menu.Guid
     *
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    private Long guid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_basic_menu.ParentID
     *
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    private Long parentid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_basic_menu.ParentName
     *
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    private String parentname;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_basic_menu.SystemCode
     *
     * @ibatorgenerated Wed Aug 31 15:20:10 GMT+08:00 2016
     */
    private String systemcode;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_basic_menu.MenuCode
     *
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    private String menucode;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_basic_menu.MenuName
     *
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    private String menuname;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_basic_menu.ContextPath
     *
     * @ibatorgenerated Tue Nov 22 13:44:15 GMT+08:00 2016
     */
    private String contextpath;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_basic_menu.Controller
     *
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    private String controller;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_basic_menu.RequestMapping
     *
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    private String requestmapping;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_basic_menu.IconPath
     *
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    private String iconpath;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_basic_menu.Description
     *
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    private String description;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_basic_menu.RootID
     *
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    private Long rootid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_basic_menu.Level
     *
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    private Integer level;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_basic_menu.Sort
     *
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    private Integer sort;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_basic_menu.IsLeaf
     *
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    private Boolean isleaf;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_basic_menu.Status
     *
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    private Boolean status;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_basic_menu.Guid
     *
     * @return the value of t_basic_menu.Guid
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    public Long getGuid() {
        return guid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_basic_menu.Guid
     *
     * @param guid the value for t_basic_menu.Guid
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    public void setGuid(Long guid) {
        this.guid = guid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_basic_menu.ParentID
     *
     * @return the value of t_basic_menu.ParentID
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    public Long getParentid() {
        return parentid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_basic_menu.ParentID
     *
     * @param parentid the value for t_basic_menu.ParentID
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_basic_menu.ParentName
     *
     * @return the value of t_basic_menu.ParentName
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    public String getParentname() {
        return parentname;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_basic_menu.ParentName
     *
     * @param parentname the value for t_basic_menu.ParentName
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_basic_menu.SystemCode
     *
     * @return the value of t_basic_menu.SystemCode
     * @ibatorgenerated Tue Nov 22 13:44:15 GMT+08:00 2016
     */
    public String getSystemcode() {
        return systemcode;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_basic_menu.SystemCode
     *
     * @param systemcode the value for t_basic_menu.SystemCode
     * @ibatorgenerated Tue Nov 22 13:44:15 GMT+08:00 2016
     */
    public void setSystemcode(String systemcode) {
        this.systemcode = systemcode;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_basic_menu.MenuCode
     *
     * @return the value of t_basic_menu.MenuCode
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    public String getMenucode() {
        return menucode;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_basic_menu.MenuCode
     *
     * @param menucode the value for t_basic_menu.MenuCode
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    public void setMenucode(String menucode) {
        this.menucode = menucode;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_basic_menu.MenuName
     *
     * @return the value of t_basic_menu.MenuName
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    public String getMenuname() {
        return menuname;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_basic_menu.MenuName
     *
     * @param menuname the value for t_basic_menu.MenuName
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_basic_menu.ContextPath
     *
     * @return the value of t_basic_menu.ContextPath
     * @ibatorgenerated Tue Nov 22 13:44:15 GMT+08:00 2016
     */
    public String getContextpath() {
        return contextpath;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_basic_menu.ContextPath
     *
     * @param contextpath the value for t_basic_menu.ContextPath
     * @ibatorgenerated Tue Nov 22 13:44:15 GMT+08:00 2016
     */
    public void setContextpath(String contextpath) {
        this.contextpath = contextpath;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_basic_menu.Controller
     *
     * @return the value of t_basic_menu.Controller
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    public String getController() {
        return controller;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_basic_menu.Controller
     *
     * @param controller the value for t_basic_menu.Controller
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    public void setController(String controller) {
        this.controller = controller;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_basic_menu.RequestMapping
     *
     * @return the value of t_basic_menu.RequestMapping
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    public String getRequestmapping() {
        return requestmapping;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_basic_menu.RequestMapping
     *
     * @param requestmapping the value for t_basic_menu.RequestMapping
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    public void setRequestmapping(String requestmapping) {
        this.requestmapping = requestmapping;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_basic_menu.IconPath
     *
     * @return the value of t_basic_menu.IconPath
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    public String getIconpath() {
        return iconpath;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_basic_menu.IconPath
     *
     * @param iconpath the value for t_basic_menu.IconPath
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    public void setIconpath(String iconpath) {
        this.iconpath = iconpath;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_basic_menu.Description
     *
     * @return the value of t_basic_menu.Description
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_basic_menu.Description
     *
     * @param description the value for t_basic_menu.Description
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_basic_menu.RootID
     *
     * @return the value of t_basic_menu.RootID
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    public Long getRootid() {
        return rootid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_basic_menu.RootID
     *
     * @param rootid the value for t_basic_menu.RootID
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    public void setRootid(Long rootid) {
        this.rootid = rootid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_basic_menu.Level
     *
     * @return the value of t_basic_menu.Level
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_basic_menu.Level
     *
     * @param level the value for t_basic_menu.Level
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_basic_menu.Sort
     *
     * @return the value of t_basic_menu.Sort
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_basic_menu.Sort
     *
     * @param sort the value for t_basic_menu.Sort
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_basic_menu.IsLeaf
     *
     * @return the value of t_basic_menu.IsLeaf
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    public Boolean getIsleaf() {
        return isleaf;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_basic_menu.IsLeaf
     *
     * @param isleaf the value for t_basic_menu.IsLeaf
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    public void setIsleaf(Boolean isleaf) {
        this.isleaf = isleaf;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_basic_menu.Status
     *
     * @return the value of t_basic_menu.Status
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_basic_menu.Status
     *
     * @param status the value for t_basic_menu.Status
     * @ibatorgenerated Wed Jul 06 10:40:14 GMT+08:00 2016
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }
}