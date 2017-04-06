package com.qi.common.web.tool;

import com.qi.common.constants.DateConstants;
import com.qi.common.constants.StatusConstants;
import com.qi.common.constants.inf.OptionEnum;
import com.qi.common.model.vo.paging.OperateVO;
import com.qi.common.model.vo.paging.TitleVO;
import com.qi.common.util.ArrayUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class ConstantsTool
 *
 * @author 张麒 2016/5/12.
 * @version Description:
 */
public class ConstantsTool extends HashMap<Object, Object> {

    private static Map<String, Class<?>> classMap = new HashMap<>();

    private static final long serialVersionUID = 118887468897535138L;

    {
        putEnum(DateConstants.Weeks.class);
        putEnum(StatusConstants.Status.class);
        putEnum(TitleVO.fieldType.class);
        putEnum(OperateVO.operateType.class);
    }

    @SuppressWarnings({"unchecked"})
    public void putEnum(Class<?> cls) {
        // 为了使用静态方法
        classMap.put(cls.getSimpleName(), cls);
        HashMap<Object, Object> constantMap = new HashMap<>();
        try {
            if (OptionEnum.class.isAssignableFrom(cls)) {
                Object[] enums = (Object[]) MethodUtils.invokeStaticMethod(cls, "values");
                List<OptionEnum> options = (List<OptionEnum>) MethodUtils.invokeStaticMethod(cls, "getOptions");
                for (Object object : enums) {
                    String name = (String) MethodUtils.invokeMethod(object, "name", ArrayUtil.EMPTY_OBJECT_ARRAY);
                    // 枚举实例名字:枚举实例
                    constantMap.put(name, object);
                    // 用于判断#if(${constant.FieldType.Text.key} == ${fieldMeta.fieldType})
                    // 枚举的名称不能和枚举的key值相同，因为constantMap的key值既有枚举名称，也有枚举的key值
                    for (OptionEnum option : options) {
                        if (StringUtils.equals(option.getKey().toString(), name)) {
                            throw new IllegalStateException(cls.getSimpleName() + " , name can't equals of key");
                        }
                    }
                    OptionEnum optionEnum = (OptionEnum) object;
                    // 用于获得显示的值$!{constant.FieldType.get($field.fieldType)}
                    // 枚举实例key字段:枚举实例value字段值
                    constantMap.put(optionEnum.getKey(), optionEnum.getValue());
                }
                // 用于循环枚举值#foreach($OptionPie in ${constant.FieldType.options})
                // <OptionPie value="${OptionPie.key}" #if(${field.fieldType} == ${OptionPie.key})selected#end>${OptionPie.value}</OptionPie>
                // 枚举options字符串:枚举实例的列表
                constantMap.put("options", options);
            }
            // 针对IdNameType
            else {
                Object[] enums = (Object[]) MethodUtils.invokeStaticMethod(cls, "values");
                // 放入枚举值
                for (Object object : enums) {
                    String name = (String) MethodUtils.invokeMethod(object, "name", ArrayUtil.EMPTY_OBJECT_ARRAY);
                    // 枚举实例名字:枚举实例
                    constantMap.put(name, object);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("failed to put enum [" + cls.getSimpleName() + "]", e);
        }
        // 枚举类名字：map
        put(cls.getSimpleName(), constantMap);
    }

    // 通过枚举类和key得到枚举实例
    public Object getEnum(String enumName, Object key) throws Exception {
        Class<?> enumClass = classMap.get(enumName);
        if (enumClass != null && key != null) {
            return MethodUtils.invokeStaticMethod(enumClass, "getValueByKey", key);
        }
        return null;
    }
}
