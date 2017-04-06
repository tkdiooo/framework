package com.qi.common.util;

import com.qi.common.constants.RpcConstants;
import com.qi.common.model.result.ActionResult;

/**
 * Class RpcUtil
 *
 * @author 张麒 2016/9/1.
 * @version Description:
 */
public class RpcUtil {

    public static <T> ActionResult<T> sendActionResult(boolean success, RpcConstants.ReplyCode replyCode, String... messages) {
        ActionResult<T> ar = new ActionResult<>();
        ar.setSuccess(success);
        ar.setReplyCode(replyCode);
        ar.setMessages(ListUtil.toList(messages));
        return ar;
    }

    public static <T> ActionResult<T> getActionResult(ActionResult<T> ar) {
        // 接口返回错误，处理错误信息
        if (!ar.getSuccess()) {
            System.out.println(ar.getMessages());
        }
        return ar;
    }

//    @SuppressWarnings("unchecked")
//    public static <T> List<T> getList(String paramKey, ActionResult<T> ar) {
//        // 接口异常捕获
////        catchException(ar);
//        List<T> result = null;
//        // 如果返回结果不为空
//        if (null != ar.getAttachs().get(paramKey)) {
//            result = (List<T>) ar.getAttachs().get(paramKey);
//        } else {
//            result = new ArrayList<>();
//        }
//        return result;
//    }
//
//    @SuppressWarnings("unchecked")
//    public static <T> T getObject(Class<T> cls, String paramKey, ActionResult ar) {
//        // 接口异常捕获
////        catchException(ar);
//        T result = null;
//        // 如果返回结果不为空
//        if (null != ar.getAttachs().get(paramKey)) {
//            result = (T) ar.getAttachs().get(paramKey);
//        }
//        return result;
//    }
//
//    public static void catchException(ActionResult ar) {
//        // 对象等于null、或者返回结果是false，抛出异常
////        if (!(null != ar && ar.getSuccess())) {
////            if (null != ar.getMessages() && ar.getMessages().size() > 0) {
////                throw new BizException(ar.getMessages().get(0));
////            } else {
////                throw new BizException("系统异常");
////            }
////        }
//    }
}
