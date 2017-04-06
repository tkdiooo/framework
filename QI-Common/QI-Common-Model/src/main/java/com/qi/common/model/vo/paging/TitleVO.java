package com.qi.common.model.vo.paging;

/**
 * Class TitleVO
 *
 * @author 张麒 2016/7/11.
 * @version Description:
 */
public class TitleVO {

    public enum fieldType {
        operate, text, textNode, multiNode, dateDash
    }

    private String code;

    private String text;

    private String title;

    private boolean sort = false;

    private Integer width;

    private fieldType field = fieldType.text;

    public TitleVO(String code, String text, String title) {
        this.code = code;
        this.text = text;
        this.title = title;
    }

    public TitleVO(String code, String text, String title, fieldType field) {
        this.code = code;
        this.text = text;
        this.title = title;
        this.field = field;
    }

    public TitleVO(String code, String text, String title, boolean sort) {
        this.code = code;
        this.text = text;
        this.title = title;
        this.sort = sort;
    }

    public TitleVO(String code, String text, String title, boolean sort, fieldType field) {
        this.code = code;
        this.text = text;
        this.title = title;
        this.sort = sort;
        this.field = field;
    }

    public TitleVO(String code, String text, String title, Integer width) {
        this.code = code;
        this.text = text;
        this.title = title;
        this.width = width;
    }

    public TitleVO(String code, String text, String title, Integer width, fieldType field) {
        this.code = code;
        this.text = text;
        this.title = title;
        this.width = width;
        this.field = field;
    }

    public TitleVO(String code, String text, String title, boolean sort, Integer width) {
        this.code = code;
        this.text = text;
        this.title = title;
        this.sort = sort;
        this.width = width;
    }

    public TitleVO(String code, String text, String title, boolean sort, Integer width, fieldType field) {
        this.code = code;
        this.text = text;
        this.title = title;
        this.sort = sort;
        this.width = width;
        this.field = field;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSort() {
        return sort;
    }

    public void setSort(boolean sort) {
        this.sort = sort;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public fieldType getField() {
        return field;
    }

    public void setField(fieldType field) {
        this.field = field;
    }
}
