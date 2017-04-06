package com.qi.common.model.vo.tree;

import java.util.List;

/**
 * 编辑页面tree控件
 *
 * @author 张麒 2016/7/4.
 * @version Description:
 */
public class RTreeVO {

    private boolean isLazy;

    private String nodeID;
    private String nodeName;
    private boolean isLeaf;

    private List<RTreeVO> children;

    public List<RTreeVO> getChildren() {
        return children;
    }

    public void setChildren(List<RTreeVO> children) {
        this.children = children;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public String getNodeID() {
        return nodeID;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }


    public boolean isLazy() {
        return isLazy;
    }

    public void setLazy(boolean lazy) {
        isLazy = lazy;
    }
}
