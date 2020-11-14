package penguin.generator.core.util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author wuxh
 * @version 1.0.0
 */
@FunctionalInterface
public interface RowParse<T> {

    T doParse(ResultSet rs) throws SQLException;
}
