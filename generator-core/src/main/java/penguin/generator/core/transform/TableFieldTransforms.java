package penguin.generator.core.transform;

import penguin.generator.core.Constants;
import penguin.generator.core.meta.MetaTable;

import java.util.Arrays;
import java.util.List;

/**
 * @author wuxh
 * @version 1.0.0
 */
public class TableFieldTransforms extends Transforms<MetaTable> {

    @Override
    protected List<Transform> transforms() {
        return Arrays.asList(new Transform("name", Constants.RULES_UNDERLINE),
                new Transform("name", Constants.RULES_UPPERCASE),
                new Transform("name", Constants.RULES_LOWERCASE),
                new Transform("name", Constants.RULES_UNDERLINE_FIRST_LOWERCASE));
    }
}
