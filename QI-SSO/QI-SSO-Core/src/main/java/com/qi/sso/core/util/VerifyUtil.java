package com.qi.sso.core.util;

import com.qi.common.util.StringUtil;
import com.qi.sso.common.constants.SSOConstants;
import com.qi.sso.model.dto.UserToken;

/**
 * Class VerifyUtil
 *
 * @author 张麒 2016/5/27.
 * @version Description:
 */
public class VerifyUtil {

    public static boolean verifyLogin(UserToken token) {
        //userLoginBean.setOperatorMsg("用户登录:传入 CookieName 为空");
        if (StringUtil.isBlank(token.getSessionId())) {
            token.setOperatorResult(SSOConstants.OperatorResult.FAIL.getKey());
            token.setOperatorCode(SSOConstants.OperatorCode.OC_0000001.getKey());
            return false;
        }

        ///userLoginBean.setOperatorMsg("用户登录:传入 LoginName 为空");
        if (StringUtil.isBlank(token.getLoginName())) {
            token.setOperatorResult(SSOConstants.OperatorResult.FAIL.getKey());
            token.setOperatorCode(SSOConstants.OperatorCode.OC_0000001.getKey());
            return false;
        }

		/*if(CString.isNull(userLoginBean.getPassword())){
            userLoginBean.setOperatorMsg("用户登录:传入 Password 为空");
			userLoginBean.setOperatorResult(false);
			return false;
		}*/

		/*
        if(CString.isNull(userLoginBean.getSpace())){
			//userLoginBean.setOperatorMsg("用户登录:传入 Space 为空");
			userLoginBean.setOperatorResult(0);
			return false;
		}*/

        //userLoginBean.setOperatorMsg("用户登录:传入 UserID 为空");
        if (StringUtil.isBlank(token.getUserID())) {
            token.setOperatorResult(SSOConstants.OperatorResult.FAIL.getKey());
            token.setOperatorCode(SSOConstants.OperatorCode.OC_0000001.getKey());
            return false;
        }
        return true;
    }


    public static boolean verifyToken(UserToken token) {
        // userLoginBean.setOperatorMsg("用户验证:传入token为空。");
        if (StringUtil.isBlank(token.getToken())) {
            token.setOperatorResult(SSOConstants.OperatorResult.FAIL.getKey());
            token.setOperatorCode(SSOConstants.OperatorCode.OC_0000001.getKey());
            return false;
        }

        // userLoginBean.setOperatorMsg("用户验证:传入Token_CacheKey为空。");
        if (StringUtil.isBlank(token.getTokenKey_Cache_Key())) {
            token.setOperatorResult(SSOConstants.OperatorResult.FAIL.getKey());
            token.setOperatorCode(SSOConstants.OperatorCode.OC_0000001.getKey());
            return false;
        }
        return true;
    }

}
