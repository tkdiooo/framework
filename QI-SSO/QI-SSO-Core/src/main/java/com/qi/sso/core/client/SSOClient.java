package com.qi.sso.core.client;

import com.qi.sso.model.dto.UserToken;

/**
 * Class SSOClient
 *
 * @author 张麒 2016/5/26.
 * @version Description:
 */
public interface SSOClient {

//     HandlerResult ssoAuthentication(final Credential credential) throws Exception;

    UserToken ssoLogin(final UserToken token) throws Exception;

    UserToken ssoLogout(final UserToken token) throws Exception;

    UserToken ssoCheck(final UserToken token) throws Exception;
}
