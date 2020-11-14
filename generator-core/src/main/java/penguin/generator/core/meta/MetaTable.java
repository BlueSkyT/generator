package penguin.generator.core.meta;

import java.util.List;

/**
 * @author wuxh
 * @version 1.0.0
 */
public class MetaTable extends MetaFunction {

    private Integer id;
    private String name;
    private String chinese;
    private String charset;

    private List<MetaColumn> columns;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public List<MetaColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<MetaColumn> columns) {
        this.columns = columns;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

}
