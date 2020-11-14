package penguin.generator.core.transform;

/**
 * @author wuxh
 * @version 1.0.0
 */
public interface NamingRulesFactory {

    NamingRules getRule(String namingRules);

    void addRule(NamingRules rules);
}
