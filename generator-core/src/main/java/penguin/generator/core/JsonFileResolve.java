package penguin.generator.core;

import penguin.generator.core.meta.MetaContext;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;

/**
 * 来自文件的元数据解析
 *
 * @author wuxh
 * @version 1.0.0
 */
public class JsonFileResolve implements MetaResolve {

    String file;

    public JsonFileResolve(String file) {
        this.file = file;
    }

    @Override
    public MetaContext resolveMeta() {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(file))) {
            String line = null;
            StringBuilder content = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            return JsonParser.fromJson(content.toString(), MetaContext.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public LinkedHashMap<String, String> resolveTypeMap() {
        LinkedHashMap<String, String> dbTypeMap = new LinkedHashMap<>();
        dbTypeMap.put("varchar", "String");
        dbTypeMap.put("int", "Integer");
        dbTypeMap.put("tinyint", "Boolean");
        return dbTypeMap;
    }
}
