package penguin.generator.core.util;

import penguin.generator.core.Constants;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * @author wuxh
 * @version 1.0.0
 */
public class FileUtil {

    public static List<File> list(File file, FileFilter fileFilter) {
        if (file.isHidden()) {
            return null;
        }
        if (file.isFile()) {
            ArrayList<File> fs = new ArrayList<>();
            fs.add(file);
            return fs;
        }
        File[] files = file.listFiles(fileFilter);
        List<File> fs = new ArrayList<>();
        for (File f : files) {
            List<File> list = list(f, fileFilter);
            if (list == null) {
                continue;
            }
            fs.addAll(list);
        }
        return fs;
    }

    /**
     * 将包路径替换成文件路径
     *
     * @param filePath
     * @return
     */
    public static String packageToPath(String filePath) {
        return filePath.replaceAll("\\" + Constants.PACKAGE_CHAR, Matcher.quoteReplacement(File.separator));
    }
}
