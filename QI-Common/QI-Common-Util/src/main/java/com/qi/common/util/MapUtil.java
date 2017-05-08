package com.qi.common.util;

import com.qi.common.tool.Logger;
import org.apache.commons.collections.MapUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Class MapUtil
 *
 * @author 张麒 2016/4/22.
 * @version Description:
 */
public class MapUtil extends MapUtils {

    private static Logger logger = new Logger(MapUtil.class);

    /**
     * 获取HashMap实例
     *
     * @return HashMap
     */
    public static <K, V> Map<K, V> getInstance() {
        return new HashMap<>();
    }

    /**
     * List转Map
     *
     * @param list List
     * @return Map<code><</code>String, V<code>></code>
     */
    public static <V> Map<String, V> toMap(List<V> list) {
        if (ListUtil.isEmpty(list)) ThrowableUtil.throwRuntimeException("list is empty");
        Map<String, V> map = new LinkedHashMap<>();
        for (int i = 0; i < list.size(); i++) {
            map.put(String.valueOf(i), list.get(i));
        }
        return map;
    }

    /**
     * List转Map
     *
     * @param list 实体集合
     * @param kp   map的key对应实体属性名称，重复会覆盖
     * @return Map<code><</code>String, V<code>></code>
     */
    public static <V> Map<String, V> toMap(List<V> list, String kp) {
        if (ListUtil.isEmpty(list)) ThrowableUtil.throwRuntimeException("list is empty");
        Map<String, V> map = new LinkedHashMap<>();
        list.forEach(v -> {
            try {
                map.put(BeanUtil.getProperty(v, kp), v);
            } catch (Exception e) {
                logger.error(ThrowableUtil.getRootMessage(e));
            }
        });
        return map;
    }

    /**
     * Object转Map
     *
     * @param obj Object
     * @return Map<code><</code>String, V<code>></code>
     */
    public static Map<String, Object> toMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        for (Class<?> cls = obj.getClass(); cls != Object.class; cls = cls.getSuperclass()) {
            for (Field field : cls.getDeclaredFields()) {
                String getterMethodName = "get" + StringUtil.capitalize(field.getName());
                Method method = BeanUtil.getAccessibleMethod(obj, getterMethodName);
                if (null != method) {
                    try {
                        map.put(field.getName(), method.invoke(obj));
                    } catch (Exception e) {
                        map.put(field.getName(), null);
                    }
                }
            }
        }
        return map;
    }

    /**
     * Map根据Key排序
     *
     * @param map   Map<code><</code>K,V>
     * @param order true：asc、false：desc
     * @param <K>   Object
     * @param <V>   Object
     * @return TreeMap<code><</code>K, V<code>></code>
     */
    public static <K, V> Map<K, V> sortByKey(Map<K, V> map, final boolean order) {
        if (isEmpty(map)) ThrowableUtil.throwRuntimeException("map is empty");

        Map<K, V> sortMap = new TreeMap<>(new MapKeyComparator<>(order));
        sortMap.putAll(map);
        return sortMap;
    }

    /**
     * Map根据Value排序
     *
     * @param map   Map<code><</code>K,V>
     * @param order true：asc、false：desc
     * @param <K>   Object
     * @param <V>   Object
     * @return LinkedHashMap<code><</code>K, V<code>></code>
     */
    public static <K, V> Map<K, V> sortByValue(Map<K, V> map, final boolean order) {
        if (isEmpty(map)) ThrowableUtil.throwRuntimeException("map is empty");

        Map<K, V> sortedMap = new LinkedHashMap<>();
        List<Map.Entry<K, V>> entryList = new ArrayList<>(map.entrySet());
        entryList.sort(new MapValueComparator<>(order));

        for (Map.Entry<K, V> entry : entryList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    private static class MapKeyComparator<K> implements Comparator<K> {
        private boolean order;

        MapKeyComparator(boolean order) {
            this.order = order;
        }

        @Override
        public int compare(K o1, K o2) {
            return ListUtil.compareTo(o1, o2, order);
        }
    }

    private static class MapValueComparator<K, V> implements Comparator<Map.Entry<K, V>> {
        private boolean order;

        MapValueComparator(boolean order) {
            this.order = order;
        }

        @Override
        public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
            return ListUtil.compareTo(o1.getValue(), o2.getValue(), order);
        }
    }

    @SuppressWarnings("unchecked")
    public static <K> K getFirstKey(Map<K, ?> map) {
        if (isEmpty(map)) ThrowableUtil.throwRuntimeException("map is empty");
        Object[] keys = map.keySet().toArray();
        return (K) keys[0];
    }

    @SuppressWarnings("unchecked")
    public static <K> K getLastKey(Map<K, ?> map) {
        if (isEmpty(map)) ThrowableUtil.throwRuntimeException("map is empty");
        Object[] keys = map.keySet().toArray();
        return (K) keys[keys.length - 1];
    }

}
