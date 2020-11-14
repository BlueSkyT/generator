package penguin.generator.core;

import penguin.generator.core.meta.MetaColumn;
import penguin.generator.core.meta.MetaContext;
import penguin.generator.core.meta.MetaTable;
import penguin.generator.core.transform.ColumnFieldTransforms;
import penguin.generator.core.transform.TableFieldTransforms;
import penguin.generator.core.util.FileUtil;

import java.io.File;
import java.util.*;

/**
 * @author wuxh
 * @version 1.0.0
 */
public abstract class AbstractGenerator implements Generator {

    protected String templatePath; //模板文件路径
    protected String sourcePath;   //生成的代码路径
    protected Renderer renderer;   //渲染器
    protected MetaResolve resolve; //元数据解析器


    TableFieldTransforms tableFieldTransforms = new TableFieldTransforms();
    ColumnFieldTransforms columnFieldTransforms = new ColumnFieldTransforms();

    public AbstractGenerator(String templatePath, String sourcePath, MetaResolve resolve, Renderer renderer) {
        this.templatePath = templatePath;
        this.sourcePath = sourcePath;
        this.resolve = resolve;
        this.renderer = renderer;
    }

    /**
     * 做一些值的转换，如表和字段的名称，字段类型映射
     *
     * @param metaContext
     * @return
     */
    protected Map<String, Object> transform(MetaContext metaContext) {
        List<MetaTable> tables = metaContext.getTables();
        Map<String, Object> content = new HashMap<>();
        List<Map<String, Object>> tableMapList = new ArrayList<>();
        content.put("tables", tableMapList);
        for (MetaTable table : tables) {
            Map<String, Object> tableMap = transform(table);
            tableMapList.add(tableMap);
        }
        return content;
    }


    /**
     * 做一些值的转换，如表和字段的名称，字段类型映射
     *
     * @return
     */
    protected Map<String, Object> transform(MetaTable table) {
        Map<String, Object> tableMap = table.toMap(new String[]{"columns"});
        tableMap.putAll(tableFieldTransforms.namingRuleTransform(table));

        List<Map<String, Object>> columnMapList = new ArrayList<>();
        tableMap.put("columns", columnMapList);

        List<MetaColumn> columns = table.getColumns();
        LinkedHashMap<String, String> typeMap = resolve.resolveTypeMap();
        for (MetaColumn column : columns) {
            String _type = typeMap.get(column.getType());
            column.setCodeType(_type);

            Map<String, Object> columnMap = column.toMap();
            columnMap.putAll(columnFieldTransforms.namingRuleTransform(column));
            columnMapList.add(columnMap);

        }
        return tableMap;
    }

    /**
     * 扫描模板
     *
     * @return
     */
    protected List<File> scanTemplate(String path) {
        File file = new File(path);
        if (file.isFile() || file.isHidden()) {
            throw new RuntimeException("请选择一个有效的目录！");
        }
        List<File> fs = FileUtil.list(file, f -> {
            if (f.isHidden()) {
                return false;
            }
            return true;
        });
        return fs;
    }

    protected String formatPath(String filePath) {
        int lastIndex = filePath.lastIndexOf(".");
        String _f = filePath.substring(lastIndex);
        return FileUtil.packageToPath(filePath.substring(0, lastIndex)) + _f;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    public MetaResolve getResolve() {
        return resolve;
    }

    public void setResolve(MetaResolve resolve) {
        this.resolve = resolve;
    }
}
