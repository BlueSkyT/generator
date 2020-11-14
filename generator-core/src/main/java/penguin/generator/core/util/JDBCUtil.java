package penguin.generator.core.util;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wuxh
 * @version 1.0.0
 */
public class JDBCUtil {

    private static <T> List<T> executeQuery(Connection connection, String sql, Object[] params, RowParse<T> parser) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            List<T> rows = new ArrayList<>();
            for (int i = 0; i < params.length; i++) {
                Object o = params[i];
                statement.setObject(i + 1, o);
            }
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    T row = parser.doParse(rs);
                    rows.add(row);
                }
                return rows;
            }
        }
    }

    public static Long count(Connection connection, String sql, Object... params) throws SQLException {
        List<Long> rows = executeQuery(connection, sql, params, rs -> rs.getLong(1));
        return rows.get(0);
    }

    private static LinkedHashMap<String, Object> parseRow(ResultSet rs, ResultSetMetaData metaData) throws SQLException {
        LinkedHashMap<String, Object> row = new LinkedHashMap<>();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            String columnName = metaData.getColumnLabel(i);
            int columnType = metaData.getColumnType(i);
            if (Types.DATE == columnType) {
                row.put(columnName, rs.getTimestamp(columnName));
            } else if (Types.TIMESTAMP == columnType) {
                row.put(columnName, rs.getTimestamp(columnName));
            } else if (Types.BLOB == columnType) {
                row.put(columnName, rs.getBlob(columnName).getBinaryStream());
            } else if (Types.CLOB == columnType) {
                try {
                    Clob clob = rs.getClob(columnName);
                    row.put(columnName, clob == null ? "" : IOUtils.toString(clob.getCharacterStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                row.put(columnName, rs.getObject(columnName));
            }
        }
        return row;
    }

    public static List<LinkedHashMap<String, Object>> find(Connection connection, String sql, Object... params) throws SQLException {
        List<LinkedHashMap<String, Object>> rows = executeQuery(connection, sql, params, rs -> {
            ResultSetMetaData metaData = rs.getMetaData();
            LinkedHashMap<String, Object> row = parseRow(rs, metaData);
            return row;
        });
        return rows;
    }

    public static List<Object> findOneColumn(Connection connection, String sql, Object... params) throws SQLException {
        List<Object> rows = executeQuery(connection, sql, params, rs -> rs.getObject(1));
        return rows;
    }

    public static Object get(Connection connection, String sql, Object... params) throws SQLException {
        return findOneColumn(connection, sql, params).get(0);
    }

    public static Map<String, Object> getRow(Connection connection, String sql, Object... params) throws SQLException {
        List<Map<String, Object>> rows = executeQuery(connection, sql, params, rs -> {
            ResultSetMetaData metaData = rs.getMetaData();
            Map<String, Object> row = parseRow(rs, metaData);
            return row;
        });
        return rows.get(0);
    }

    public static void update(Connection connection, String sql, Object... params) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                Object o = params[i];
                statement.setObject(i + 1, o);
            }
            statement.executeUpdate();
        }
    }

    /**
     * 通用查询方法，且支持转换为对象
     *
     * @param clz
     * @param connection
     * @param sql
     * @param params
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T> findObjects(Class<T> clz, Connection connection, String sql, Object... params) throws Exception {
        List<LinkedHashMap<String, Object>> rows = JDBCUtil.find(connection, sql, params);
        List<T> _rows = BeanUtil.copyToBeans(rows, clz);
        return _rows;
    }
}
