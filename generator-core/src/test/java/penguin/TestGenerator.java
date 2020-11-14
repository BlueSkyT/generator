package penguin;

import org.junit.Before;
import org.junit.Test;
import penguin.generator.core.*;
import penguin.generator.core.meta.MetaColumn;
import penguin.generator.core.meta.MetaContext;
import penguin.generator.core.meta.MetaTable;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * @author wuxh
 * @version 1.0.0
 */
public class TestGenerator {

    Renderer renderer = new FreeMarkerRenderer();
    String templatePath = "templatePath";
    String sourcePath = "sourcePath";

    @Before
    public void before() {

    }

    /**
     * json + freemarker：
     */
//    @Test
    public void testFromFileToFreeMarker() {
        MetaResolve resolve = new JsonFileResolve("gen.txt");
        CRUDGenerator generator = new CRUDGenerator(templatePath, sourcePath, resolve, renderer);
        generator.generate();
    }

    /**
     * jdbc + freemarker
     */
//    @Test
    public void testFromDBToFreeMarker() {
        String url = "jdbc:mysql://xxxx:3306/xxx?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8";
        String username = "username";
        String password = "password";
        MetaResolve resolve = new JdbcResolve(url, username, password, "com.mysql.cj.jdbc.Driver");

        CRUDGenerator generator = new CRUDGenerator(templatePath, sourcePath, resolve, renderer);
        generator.generate();
    }

    /**
     * 自定义解析 + freemarker
     */
//    @Test
    public void testCustomResole() {
        MetaResolve resolve = new MetaResolve() {
            @Override
            public MetaContext resolveMeta() {
                MetaContext context = new MetaContext();
                context.setTables(new ArrayList<>());
                context.setNamingRules(Constants.RULES_LOWERCASE);
                MetaTable table = new MetaTable();
                table.setName("user");
                context.getTables().add(table);

                table.setColumns(new ArrayList<>());
                MetaColumn column = new MetaColumn();
                column.setName("username");
                column.setType("varchar");
                column.setLength(100);
                table.getColumns().add(column);

                MetaColumn column1 = new MetaColumn();
                column1.setName("password");
                column1.setType("varchar");
                column1.setLength(100);
                table.getColumns().add(column1);
                return context;
            }

            @Override
            public LinkedHashMap<String, String> resolveTypeMap() {
                LinkedHashMap<String, String> dbTypeMap = new LinkedHashMap<>();
                dbTypeMap.put("varchar", "String");
                dbTypeMap.put("int", "Integer");
                dbTypeMap.put("tinyint", "Boolean");
                return dbTypeMap;
            }
        };

        CRUDGenerator generator = new CRUDGenerator(templatePath, sourcePath, resolve, renderer);
        generator.generate();
    }
}
