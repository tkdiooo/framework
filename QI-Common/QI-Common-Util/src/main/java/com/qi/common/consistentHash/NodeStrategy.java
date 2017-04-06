package com.qi.common.consistentHash;

/**
 * Class NodeStrategy
 *
 * @author 张麒 2016/5/16.
 * @version Description:
 */
public interface NodeStrategy {
    /**
     * 得到下个节点
     * @return
     */
    public Node nextNode();
    /**
     * 添加节点
     * @param n
     */
    public void addNode(Node n);
    /**
     * 移除节点
     * @param node
     * @return
     */
    public boolean removeNode(Node node);
}
