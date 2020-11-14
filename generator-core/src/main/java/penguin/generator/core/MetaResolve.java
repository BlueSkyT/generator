package penguin.generator.core;

import penguin.generator.core.meta.MetaContext;

import java.util.LinkedHashMap;

/**
 * 元数据解析器
 * 数据可能来自文件、数据库等
 *
 * @author wuxh
 * @version 1.0.0
 */
public interface MetaResolve {

    /**
     * 解析数据，获取返回的结构
     *
     * @return
     */
    public MetaContext resolveMeta();

    /**
     * 字段类型
     * key: 元字段类型，eg: varchar
     * value: 源码字段, eg: String
     *
     * @return
     */
    public LinkedHashMap<String, String> resolveTypeMap();
}
