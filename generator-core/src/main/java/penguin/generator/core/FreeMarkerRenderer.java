package penguin.generator.core;

import penguin.generator.core.meta.MetaContext;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.Map;

/**
 * @author wuxh
 * @version 1.0.0
 */
public class FreeMarkerRenderer implements Renderer {

    Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);

    public FreeMarkerRenderer() {
        cfg.setDefaultEncoding("UTF-8");
    }

    @Override
    public boolean isRender(String text) {
        return text.indexOf("${") > 0;
    }

    @Override
    public void beforeRender(MetaContext context) {
        //do nothing
    }

    @Override
    public String renderText(Map<String, Object> data, String path) {
        try {
            StringWriter writer = new StringWriter();
            Template t = new Template(path, path, cfg);
            t.process(data, writer);
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException("路径解析失败！", e);
        }
    }


    @Override
    public boolean renderFileContent(Map<String, Object> data, File root, File template, File outFile) {
        try {
            String name = template.getAbsolutePath().replace(root.getAbsolutePath(), "");
            Template freemarkerTemplate = new Template(name, new FileReader(template), cfg);
            freemarkerTemplate.process(data, new OutputStreamWriter(new FileOutputStream(outFile)));
            return true;
        } catch (Exception e) {
            throw new RuntimeException("模板解析失败！", e);
        }
    }
}
