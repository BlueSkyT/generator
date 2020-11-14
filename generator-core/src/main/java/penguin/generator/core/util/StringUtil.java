package penguin.generator.core.util;

/**
 * @author wuxh
 * @version 1.0.0
 */
public class StringUtil {

    public static String firstUpperCase(String str) {
        String s1 = str.substring(0, 1).toUpperCase();
        return s1 + str.substring(1);
    }
}
