package MySpring.Spring;


import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version: 1.0
 * @description:
 * @author: zhiwei.liao
 * @create: 2020-12-30 13:34:20
 **/
public class LzwApplicationContext {

    private Map<String, Object> map = new ConcurrentHashMap();

    private Map<String ,BeanDefinition> beanDefinitions = new HashMap<>();

    public LzwApplicationContext(Class config) throws  ClassNotFoundException{
        scan(new Class[]{config});
        newInstance();
    }

    public Object getBean(String beanName) throws InstantiationException, IllegalAccessException {
        if (map.containsKey(beanName)){
            return map.get(beanName);
        }else if (beanDefinitions.containsKey(beanName)){
            return createBean(beanDefinitions.get(beanName));
        }else {
            throw new RuntimeException("class not find!");
        }
    }

    public void scan(Class[] configs) throws ClassNotFoundException {
        for (Class config :
                configs) {
            ComponentScan annotation = (ComponentScan) config.getAnnotation(ComponentScan.class);
            String value = annotation.value();
            System.out.println(value);
            String s = value.replace(".", "/");
            System.out.println(s);
            ClassLoader classLoader = LzwApplicationContext.class.getClassLoader();
            URL resource = classLoader.getResource(s);
            File file = new File(resource.getFile());
            File[] files = file.listFiles();
            for (File f :
                    files) {
                System.out.println(f.getAbsolutePath());
                String replace = f.getAbsolutePath().substring(f.getAbsolutePath().indexOf("MySpring"), f.getAbsolutePath().indexOf(".class")).replace("/", ".");
                System.out.println(replace);
                Class<?> aClass = classLoader.loadClass(replace);
                if (aClass.isAnnotationPresent(Component.class)) {
                    BeanDefinition beanDefinition = new BeanDefinition();
                    beanDefinition.setType(aClass);
                    Component component = aClass.getAnnotation(Component.class);
                    String value1 = component.value();
                    beanDefinition.setBeanName(value1);
                    if (aClass.isAnnotationPresent(Scope.class)) {
                        Scope annotation1 = aClass.getAnnotation(Scope.class);
                        beanDefinition.setScope(annotation1.value());
                    } else {
                        beanDefinition.setScope("simple");
                    }
                    beanDefinitions.put(value1,beanDefinition);
                }
            }
        }
    }

    public void newInstance(){
        this.beanDefinitions.values().forEach(item->{
            if ("simple".equals(item.getScope())){
                try {
                    map.put(item.getBeanName(),createBean(item));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Object createBean(BeanDefinition beanDefinition) throws IllegalAccessException, InstantiationException {
        Object o = beanDefinition.getType().newInstance();
        Class clazz = beanDefinition.getType();
        for (Field field : clazz.getDeclaredFields()) {

            if (field.isAnnotationPresent(Autowired.class)){
                String name = field.getName();
                Object bean = getBean(name);
                if (bean == null){
                    bean = createBean(beanDefinitions.get(name));
                }
                field.setAccessible(true);
                field.set(o, bean);
            }
        }
        return o;
    }
}
