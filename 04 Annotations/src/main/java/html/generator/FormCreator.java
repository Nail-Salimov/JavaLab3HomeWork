package html.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FormCreator {


    private String html;
    private final Configuration configuration;

    public FormCreator(String action, String method) {
        this.configuration = new Configuration(Configuration.VERSION_2_3_23);

        try {
            configuration.setDirectoryForTemplateLoading(new File("/home/nail/Progy/JavaLab3/JavaLab4/src/main/resources/templates/"));
            Template template = configuration.getTemplate("form.ftl");

            Map<String, Object> root = new HashMap<>();

            root.put("action", action);
            root.put("method", method);

            Writer out = new StringWriter();
            template.process(root, out);

            html=out.toString() +"\n";
        } catch (IOException | TemplateException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void addInput(String type, String name, String placeholder) {
        try {
            Template template = configuration.getTemplate("input.ftl");

            Map<String, Object> root = new HashMap<>();

            root.put("type", type);
            root.put("name", name);
            root.put("placeholder", placeholder);

            Writer out = new StringWriter();
            template.process(root, out);

            html+="    " + out.toString() + "\n";
        } catch (IOException | TemplateException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void create(Path path) {
        try {
            html += "</form>\n";
            BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()));
            writer.write(html);
            writer.close();
        }catch (IOException e){
            throw new IllegalArgumentException(e);
        }
    }
}
