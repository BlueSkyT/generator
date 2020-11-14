package penguin.generator.core.util;

import java.io.IOException;
import java.io.Reader;

/**
 * @author wuxh
 * @version 1.0.0
 */
public class IOUtils {

    public static String toString(Reader reader) throws IOException {
        char[] bs = new char[512];
        StringBuilder sb = new StringBuilder();
        while ((reader.read(bs)) != 0) {
            sb.append(bs);
        }
        return sb.toString();
    }
}
