package html.generator;

import com.google.auto.service.AutoService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@AutoService(Processor.class)
@SupportedAnnotationTypes(value = {"html.generator.HtmlForm"})
public class HtmlProcessor extends AbstractProcessor {

    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(HtmlForm.class);

        for (Element element : annotatedElements) {
            String path = HtmlProcessor.class.getProtectionDomain().getCodeSource().getLocation().getPath();

            path = "/" + path.substring(1) + element.getSimpleName().toString() + ".html";
            Path out = Paths.get(path);

            List<? extends Element> enclosingElements = element.getEnclosedElements();
            List<HtmlInput> inputs = new LinkedList<>();

            //получение всех строк с аннотацией HtmlInput
            for (Element e : enclosingElements) {
                if (!(e instanceof ExecutableElement)) {
                    HtmlInput htmlInput = e.getAnnotation(HtmlInput.class);
                    if (htmlInput != null) {
                        inputs.add(htmlInput);
                    }
                }
            }

            //создание формы на freemarker
            HtmlForm annotation = element.getAnnotation(HtmlForm.class);
            FormCreator creator = new FormCreator(annotation.action(), annotation.method());
            for (HtmlInput input : inputs){
                creator.addInput(input.type(), input.name(), input.placeholder());
            }
            creator.create(out);

        }
        return true;
    }
}