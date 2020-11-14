package penguin.generator.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wuxh
 * @version 1.0.0
 */
public class BeanUtil {

    public static void copyToBean(Map<String, Object> properties, Object bean) throws Exception {
        Class<?> aClass = bean.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            if (properties.containsKey(name)) {
                Object value = properties.get(name);
                String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
                Method method = aClass.getMethod(methodName, field.getType());
                if ("boolean".equals(field.getType().getName()) && value != null) {
                    value = Boolean.parseBoolean(value.toString());
                }
                method.invoke(bean, value);
            }
        }
    }

    public static <T> List<T> copyToBeans(List<LinkedHashMap<String, Object>> properties, Class<T> bean) throws Exception {
        if (properties == null || properties.isEmpty()) {
            return null;
        }
        List<T> rows = new ArrayList<>();
        for (Map<String, Object> property : properties) {
            T t = bean.newInstance();
            copyToBean(property, t);
            rows.add(t);
        }
        return rows;
    }
}
