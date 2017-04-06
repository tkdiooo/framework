package com.qi.sso.core.client;

import com.qi.common.facade.hessian.BaseClient;
import com.qi.sso.model.dto.UserToken;

/**
 * Class SSOHessianClient
 *
 * @author 张麒 2016/5/27.
 * @version Description:
 */
public class SSOHessianClient extends BaseClient<SSOClient> implements SSOClient {

    public SSOHessianClient(final long connTimeOut, final long readTimeOut, final String url) {
        super(connTimeOut, readTimeOut, url);
    }

    @Override
    public UserToken ssoLogin(UserToken token) throws Exception {
        return getClient().ssoLogin(token);
    }

    @Override
    public UserToken ssoLogout(UserToken token) throws Exception {
        return getClient().ssoLogout(token);
    }

    @Override
    public UserToken ssoCheck(UserToken token) throws Exception {
        return getClient().ssoCheck(token);
    }
}
