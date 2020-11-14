package penguin.generator.core;

import penguin.generator.core.meta.MetaColumn;
import penguin.generator.core.meta.MetaConfig;
import penguin.generator.core.meta.MetaContext;
import penguin.generator.core.meta.MetaTable;
import penguin.generator.core.util.JDBCUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author wuxh
 * @version 1.0.0
 */
public class JdbcResolve implements MetaResolve {

    private String url;
    private String username;
    private String password;
    private String driver;

    public JdbcResolve(String url, String username, String password, String driver) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.driver = driver;
    }

    public Connection getConnection() {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("JDBC 连接出错！");
        }
    }

    @Override
    public MetaContext resolveMeta() {
        MetaContext context = new MetaContext();
        try (Connection connection = getConnection()) {
            //获取表格解析
            List<MetaConfig> configs = JDBCUtil.findObjects(MetaConfig.class, connection, "select * from meta_config ");
            context.parseConfig(configs);
            //获取表格解析
            List<MetaTable> tables = JDBCUtil.findObjects(MetaTable.class, connection, "select * from meta_table ");
            context.setTables(tables);
            for (MetaTable table : tables) {
                //解析字段
                List<MetaColumn> columns = JDBCUtil.findObjects(MetaColumn.class, connection, "select * from meta_column where tableId=?", table.getId());
                table.setColumns(columns);
            }
            return context;
        } catch (Exception e) {
            throw new RuntimeException("加载表配置信息出错！", e);
        }
    }

    @Override
    public LinkedHashMap<String, String> resolveTypeMap() {
        LinkedHashMap<String, String> dbTypeMap = new LinkedHashMap<>();
        //TODO: 正确的姿势应该是从jdbc中获取
        dbTypeMap.put("varchar", "String");
        dbTypeMap.put("int", "Integer");
        dbTypeMap.put("tinyint", "Boolean");
        return dbTypeMap;
    }
}
