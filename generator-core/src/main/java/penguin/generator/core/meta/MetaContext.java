package penguin.generator.core.meta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wuxh
 * @version 1.0.0
 */
public class MetaContext {

    private String namingRules; //命名规则：驼峰、下划线、
    private Map<String, Object> property = new HashMap<>();
    private List<MetaTable> tables;

    public void parseConfig(List<MetaConfig> configs) {
        if (configs == null) {
            return;
        }
        configs.forEach(config -> {
            this.property.put(config.getKey(), config.getValue());
        });
    }

    public String getNamingRules() {
        return namingRules;
    }

    public void setNamingRules(String namingRules) {
        this.namingRules = namingRules;
    }

    public Map<String, Object> getProperty() {
        return property;
    }

    public void setProperty(Map<String, Object> property) {
        this.property = property;
    }

    public List<MetaTable> getTables() {
        return tables;
    }

    public void setTables(List<MetaTable> tables) {
        this.tables = tables;
    }

}
