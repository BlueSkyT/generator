package penguin.generator.core.transform;

import penguin.generator.core.Constants;
import penguin.generator.core.util.StringUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wuxh
 * @version 1.0.0
 */
public abstract class Transforms<T> implements NamingRulesFactory {

    List<NamingRules> rules = new ArrayList<>();

    @Override
    public NamingRules getRule(String namingRules) {
        for (NamingRules rule : rules) {
            if (namingRules.equals(rule.namingRules())) {
                return rule;
            }
        }
        return null;
    }

    @Override
    public void addRule(NamingRules rules) {

    }

    public Map<String, Object> namingRuleTransform(T o) {
        List<Transform> transforms = transforms();
        Map<String, Object> values = new HashMap<>();
        if (transforms == null) {
            return values;
        }

        transforms.forEach(transform -> values.put(transform.newFieldName(), transform.transform(o)));
        return values;
    }

    protected abstract List<Transform> transforms();

    class Transform {
        String field;      //字段
        String namingRules;//规则
        NamingRules rule;

        public Transform(String field, String namingRules) {
            this.field = field;
            this.namingRules = namingRules;
            this.rule = Transforms.this.getRule(namingRules);
        }

        String newFieldName() {
            return rule.newFieldName(field);
        }

        String transform(T o) {
            String _value = null;
            try {
                Field _field = o.getClass().getDeclaredField(field);
                _field.setAccessible(true);
                Object value = _field.get(o);
                _value = rule.namingRuleTransform((String) value);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return _value;
        }
    }


    Transforms() {
        rules.add(new NamingRules() {
            @Override
            public String namingRules() {
                return Constants.RULES_LOWERCASE;
            }

            @Override
            public String namingRuleTransform(String name) {
                return name.toLowerCase();
            }

        });
        rules.add(new NamingRules() {
            @Override
            public String namingRules() {
                return Constants.RULES_UPPERCASE;
            }

            @Override
            public String namingRuleTransform(String name) {
                return name.toUpperCase();
            }

        });
        rules.add(new NamingRules() {
            @Override
            public String namingRules() {
                return Constants.RULES_UNDERLINE;
            }

            @Override
            public String namingRuleTransform(String name) {
                String[] ss = name.split("_");
                StringBuilder newName = new StringBuilder();
                for (String s : ss) {
                    newName.append(StringUtil.firstUpperCase(s));
                }
                return newName.toString();
            }

        });
        rules.add(new NamingRules() {
            @Override
            public String namingRules() {
                return Constants.RULES_UNDERLINE_FIRST_LOWERCASE;
            }

            @Override
            public String namingRuleTransform(String name) {
                String[] ss = name.split("_");
                StringBuilder newName = new StringBuilder();
                for (int i = 0; i < ss.length; i++) {
                    String s = ss[i];
                    if (i == 0) {
                        newName.append(s);
                    } else {
                        newName.append(StringUtil.firstUpperCase(s));
                    }
                }
                return newName.toString();
            }

        });
    }
}
