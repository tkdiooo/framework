package com.qi.common.database.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class BaseDtoAdapter
 *
 * @author 张麒 2016/5/16.
 * @version Description:
 */
public class BaseDtoAdapter extends BaseDtoImpl {


    protected final static Logger logger = LoggerFactory.getLogger(BaseDtoAdapter.class);

    private static final long serialVersionUID = -5667483166249714827L;

    public static final int FLAG_NO = 0;

    public static final int FLAG_YES = 1;

    public static final int IS_DELETE_NO = 0;

    public static final int IS_DELETE_YES = 1;

    //分区的原则
    private HashMap<String, Object> patitionLogo = null;

    public String toString() {

        StringBuilder propBuffer = new StringBuilder();

        Field[] fields = this.getClass().getDeclaredFields();

        propBuffer.append("[").append(this.getClass()).append("]");

        for (Field field : fields) {

            String fieldName = field.getName();

            Object fieldValue = null;

            String getterMethod = "get"
                    + Character.toUpperCase(fieldName.charAt(0))
                    + fieldName.substring(1);
            try {
                Method getMethod = this.getClass().getMethod(getterMethod,
                        (Class[]) null);

                Object o = getMethod.invoke(this, (Object[]) null);

                if (o instanceof Map || o instanceof List) {

                    continue;
                }

                if (o instanceof IBaseDto) {
                    fieldName += "Id";
                    fieldValue = getPrimaryKeyValue((IBaseDto) o);
                } else {
                    fieldValue = o;
                }

                propBuffer.append(fieldName).append(":").append(fieldValue)
                        .append(",");

            } catch (Exception e) {
            }

        }

        return propBuffer.substring(0, propBuffer.length() - 1);
    }

    private static Object getPrimaryKeyValue(IBaseDto o) throws Exception {

        String fieldName = getPrimaryKeyFieldName(o);

        if (fieldName == null) {

            return null;
        }

        String getterMethod = "get"
                + Character.toUpperCase(fieldName.charAt(0))
                + fieldName.substring(1);

        try {

            Method getMethod = o.getClass().getMethod(getterMethod,
                    (Class[]) null);

            return getMethod.invoke(o, (Object[]) null);

        } catch (Exception e) {

            e.printStackTrace();

            logger.error("Could not invoke method '" + getterMethod + "' on "
                    + ClassUtils.getShortName(o.getClass()));

            throw new Exception("");
        }
    }

    private static String getPrimaryKeyFieldName(IBaseDto o) {

        Entity entity = o.getClass().getAnnotation(Entity.class);

        if (entity == null) {

            return null;
        }

        return "".equals(entity.Pk()) ? null : entity.Pk();
    }

    public HashMap<String, Object> getPatitionLogo() {
        return patitionLogo;
    }

    public void setPatitionLogo(HashMap<String, Object> patitionLogo) {
        this.patitionLogo = patitionLogo;
    }
}
