package com.qi.sso.core.client;

import com.qi.common.util.Dom4jUtil;
import com.qi.sso.model.dto.UserToken;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

/**
 * Class SSOWebServiceClient
 *
 * @author 张麒 2016/5/27.
 * @version Description:
 */
public class SSOWebServiceClient implements SSOClient {

    private Client client;

    public SSOWebServiceClient(final String url, final Long connTimeOut) {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        client = dcf.createClient(url);
        HTTPConduit conduit = (HTTPConduit) client.getConduit();
        HTTPClientPolicy policy = conduit.getClient();
        policy.setConnectionTimeout(connTimeOut);//单位是毫秒
    }

    @Override
    public UserToken ssoLogin(UserToken token) throws Exception {
        Object[] resultObj = client.invoke("tokenSSOCheck", token.getToken(), token.getTokenKey_Cache_Key());
        return Dom4jUtil.readXML((String) resultObj[0], UserToken.class);
    }

    @Override
    public UserToken ssoLogout(UserToken token) throws Exception {
        Object[] resultObj = client.invoke("userSSOLogin", token.getSessionId(), token.getUserID(), token.getLoginName());
        return Dom4jUtil.readXML((String) resultObj[0], UserToken.class);
    }

    @Override
    public UserToken ssoCheck(UserToken token) throws Exception {
        Object[] resultObj = client.invoke("userSSOLogout", token.getToken(), token.getTokenKey_Cache_Key());
        return Dom4jUtil.readXML((String) resultObj[0], UserToken.class);
    }
}
