package com.qi.common.model.abs;

import com.qi.common.constants.RpcConstants.ReplyCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class BasicResult
 *
 * @author 张麒 2017/3/21.
 * @version Description:
 */
public abstract class BasicResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 响应状态
     */
    protected boolean success = false;
    /**
     * 应答代码
     */
    protected ReplyCode replyCode;
    /**
     * 响应消息列表
     */
    protected List<String> messages = new ArrayList<>();

    public boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ReplyCode getReplyCode() {
        return replyCode;
    }

    public void setReplyCode(ReplyCode replyCode) {
        this.replyCode = replyCode;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }
}
