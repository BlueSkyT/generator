package penguin.generator.core.transform;

/**
 * @author wuxh
 * @version 1.0.0
 */
public interface NamingRules {

    public String namingRules();

    public String namingRuleTransform(String name);

    default String newFieldName(String fieldName) {
        return fieldName + "_" + namingRules();
    }
}
