package penguin.generator.core;

import penguin.generator.core.meta.MetaContext;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wuxh
 * @version 1.0.0
 */
public class CRUDGenerator extends AbstractGenerator {

    public CRUDGenerator(String templatePath, String sourcePath, MetaResolve resolve, Renderer renderer) {
        super(templatePath, sourcePath, resolve, renderer);
    }

    @Override
    public boolean generate() {
        //1.获取表描述信息
        MetaContext metaContext = getResolve().resolveMeta();
        //3.渲染文件
        renderer.beforeRender(metaContext);
        //4.转换相关属性
        Map<String, Object> data = new HashMap<>();
        metaContext.getTables().forEach(metaTable -> {
            Map<String, Object> tableData = transform(metaTable);
            data.put(Constants.CURRENT_TABLE_PROPERTY, tableData);
            renderTable(renderer, data);
        });
        return true;
    }

    /**
     * 渲染一个表
     *
     * @param renderer
     * @param data
     */
    private void renderTable(Renderer renderer, Map<String, Object> data) {
        final File root = new File(templatePath);
        File out = new File(sourcePath);
        //获取模板内容
        List<File> files = scanTemplate(templatePath);
        for (File file : files) {
            if (file.isHidden() || file.isDirectory()) {
                continue;
            }
            //获取到相对路径
            String filePath = file.getAbsolutePath().replace(root.getAbsolutePath(), "");
            //如果支持渲染路径
            if (renderer.isRender(file.getAbsolutePath())) {
                filePath = renderer.renderText(data, filePath);
            }
            filePath = formatPath(filePath);
            File outFile = new File(out.getAbsolutePath(), filePath);
            boolean ok = outFile.getParentFile().exists() || outFile.getParentFile().mkdirs();
            if (!ok) {
                throw new RuntimeException("文件夹创建失败！");
            }
            renderer.renderFileContent(data, root, file, outFile);
        }
    }
}
