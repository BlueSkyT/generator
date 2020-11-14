package penguin.generator.core;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author wuxh
 * @version 1.0.0
 */
public class JsonParser {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("to json error!");
        }
    }

    public static <T> T fromJson(String json, Class<T> valueType) {
        try {
            return mapper.readValue(json, valueType);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("from json error!");
        }
    }
}
