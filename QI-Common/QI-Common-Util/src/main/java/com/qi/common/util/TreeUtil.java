package com.qi.common.util;

import com.qi.common.model.vo.tree.RTreeVO;
import com.qi.common.model.vo.tree.ZTreeVO;

/**
 * Class TreeUtil
 *
 * @author 张麒 2016/7/6.
 * @version Description:
 */
public class TreeUtil {

    public static <T> RTreeVO convertRTree(T result, String nodeID, String nodeName, String isLeaf) {
        RTreeVO tree = new RTreeVO();
        tree.setLazy(false);
        tree.setNodeID(String.valueOf(BeanUtil.getPropertyValue(result, nodeID)));
        tree.setNodeName((String) BeanUtil.getPropertyValue(result, nodeName));
        if (StringUtil.isNoneBlank(isLeaf)) {
            tree.setLeaf((Boolean) BeanUtil.getPropertyValue(result, isLeaf));
        } else {
            tree.setLeaf(true);
        }
        return tree;
    }

    public static <T> ZTreeVO convertZTree(T result, String treeID, String pID, String treeName, String isParent) {
        ZTreeVO tree = new ZTreeVO();
        tree.setId(String.valueOf(BeanUtil.getPropertyValue(result, treeID)));
        tree.setPId(String.valueOf(BeanUtil.getPropertyValue(result, pID)));
        tree.setName(String.valueOf(BeanUtil.getPropertyValue(result, treeName)));
        tree.setIsParent(!(Boolean) BeanUtil.getPropertyValue(result, isParent));
        return tree;
    }
}
