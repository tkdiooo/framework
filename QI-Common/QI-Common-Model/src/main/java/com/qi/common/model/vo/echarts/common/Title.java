package com.qi.common.model.vo.echarts.common;

import com.qi.common.constants.EchartsConstants.HorizAlign;

/**
 * Class Title
 *
 * @author 张麒 2016/9/12.
 * @version Description:
 */
public class Title {

    public enum Target {
        self, blank
    }

    private Boolean show;

    private String text;
    private String link;
    private Target target;
    private TextStyle textStyle;

    private String subtext;
    private String sublink;
    private Target subtarget;
    private TextStyle subtextStyle;

    private HorizAlign x;

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public TextStyle getTextStyle() {
        return textStyle;
    }

    public void setTextStyle(TextStyle textStyle) {
        this.textStyle = textStyle;
    }

    public String getSubtext() {
        return subtext;
    }

    public void setSubtext(String subtext) {
        this.subtext = subtext;
    }

    public String getSublink() {
        return sublink;
    }

    public void setSublink(String sublink) {
        this.sublink = sublink;
    }

    public Target getSubtarget() {
        return subtarget;
    }

    public void setSubtarget(Target subtarget) {
        this.subtarget = subtarget;
    }

    public TextStyle getSubtextStyle() {
        return subtextStyle;
    }

    public void setSubtextStyle(TextStyle subtextStyle) {
        this.subtextStyle = subtextStyle;
    }

    public HorizAlign getX() {
        return x;
    }

    public void setX(HorizAlign x) {
        this.x = x;
    }
}
