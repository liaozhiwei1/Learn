package MySpring.Spring;


import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Annotated;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version: 1.0
 * @description:
 * @author: zhiwei.liao
 * @create: 2020-12-30 13:34:20
 **/
public class LzwApplicationContext {

    private Map<String ,Object> map = new ConcurrentHashMap();

    public LzwApplicationContext(Class config) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        ComponentScan annotation = (ComponentScan) config.getAnnotation(ComponentScan.class);
        String value = annotation.value();
        System.out.println(value);
        String s = value.replace(".", "/");
        System.out.println(s);
        ClassLoader classLoader = LzwApplicationContext.class.getClassLoader();
        URL resource = classLoader.getResource(s);
        File file = new File(resource.getFile(),"");
        File[] files = file.listFiles();
        for (File f :
                files) {
            System.out.println(f.getAbsolutePath());
            String replace = f.getAbsolutePath().substring(f.getAbsolutePath().indexOf("MySpring"), f.getAbsolutePath().indexOf(".class")).replace("\\", ".");
            System.out.println(replace);
            Class<?> aClass = classLoader.loadClass(replace);
            Annotation[] annotations = aClass.getAnnotations();
            for (Annotation a :
                    annotations) {
                if (a instanceof Component){
                    Component annotation1 = aClass.getAnnotation(Component.class);
                    Object o = aClass.newInstance();

                }
            }
        }
    }
}
