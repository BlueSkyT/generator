package penguin.generator.core.meta;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author wuxh
 * @version 1.0.0
 */
public abstract class MetaFunction {


    private Map<String, Object> values;

    public Map<String, Object> toMap(String[] ignoreFields) {
        Map<String, Object> content = new HashMap<>();
        Field[] fields = this.getClass().getDeclaredFields();
        List<String> _ignoreFields = ignoreFields();
        if (_ignoreFields == null) {
            _ignoreFields = new ArrayList<>();
        }
        if (ignoreFields != null) {
            Collections.addAll(_ignoreFields, ignoreFields);
        }
        for (Field field : fields) {
            if (_ignoreFields.contains(field.getName())) {
                continue;
            }
            try {
                field.setAccessible(true);
                content.put(field.getName(), field.get(this));
            } catch (Exception e) {
                throw new RuntimeException("meta content toMap error!", e);
            }
        }
        return content;
    }

    public Map<String, Object> getValues() {
        return values;
    }

    public void setValues(Map<String, Object> values) {
        this.values = values;
    }

    public Map<String, Object> toMap() {
        return toMap(null);
    }

    /**
     * 忽略的字段
     *
     * @return
     */
    protected List<String> ignoreFields() {
        return null;
    }
}
