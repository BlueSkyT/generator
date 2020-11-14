package penguin.generator.core;

import penguin.generator.core.meta.MetaContext;

import java.io.File;
import java.util.Map;

/**
 * @author wuxh
 * @version 1.0.0
 */
public interface Renderer {

    /**
     * 是否需要渲染
     *
     * @param text
     * @return
     */
    boolean isRender(String text);

    /**
     * 渲染之前
     *
     * @param context
     */
    void beforeRender(MetaContext context);

    /**
     * 渲染文件路径
     *
     * @param data
     * @param path
     * @return
     */
    public String renderText(Map<String, Object> data, String path);

    /**
     * 渲染内容
     *
     * @param data
     * @param root
     * @param template
     * @param outFile
     * @return
     */
    public boolean renderFileContent(Map<String, Object> data, File root, File template, File outFile);
}
