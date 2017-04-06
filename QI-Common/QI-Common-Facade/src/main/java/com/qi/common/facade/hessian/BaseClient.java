package com.qi.common.facade.hessian;

import com.caucho.hessian.client.HessianProxyFactory;
import com.qi.common.facade.annotation.HessianNamespace;
import com.qi.common.tool.Logger;
import com.qi.common.tool.Assert;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.util.ResourceBundle;

/**
 * Class BaseClient
 *
 * @author 张麒 2016/5/26.
 * @version Description:
 */
public abstract class BaseClient<T> {

    private static final Logger logger = new Logger(BaseClient.class);

    protected HessianProxyFactory factory;
    protected String SERVICE_URL = "";

    private Class<T> apiClass;

    @SuppressWarnings("unchecked")
    private void initialize() {
        factory = new HessianProxyFactory();
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        apiClass = (Class<T>) params[0];
    }

    public BaseClient() {
        initialize();
        HessianNamespace annotation = getClass().getAnnotation(HessianNamespace.class);
        Assert.notNull(annotation, "hessian client not find HessianNamespace annotation");
        ResourceBundle rb = ResourceBundle.getBundle("hessian");
        factory.setConnectTimeout(Long.valueOf(rb.getString("hessian.timeout.connect")));
        factory.setReadTimeout(Long.valueOf(rb.getString("hessian.timeout.read")));
        this.SERVICE_URL = rb.getString("hessian.client.url") + annotation.value();
    }

    public BaseClient(final long connTimeOut, final long readTimeOut, final String fullUrl) {
        initialize();
        factory.setConnectTimeout(connTimeOut);
        factory.setReadTimeout(readTimeOut);
        this.SERVICE_URL = fullUrl;
    }

    @SuppressWarnings("unchecked")
    protected T getClient() {
        T client = null;
        try {
            logger.info("接口调用类：" + apiClass.getName() + "。调用URL：" + SERVICE_URL);
            client = (T) factory.create(apiClass, SERVICE_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return client;
    }
}
