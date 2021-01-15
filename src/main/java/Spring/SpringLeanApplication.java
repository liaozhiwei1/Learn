package Spring;


import MyBatis.testMapper;
import Spring.Service.ServiceA;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;

@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan(value = "MyBatis")
public class SpringLeanApplication implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    public static void main(String[] args) {
        SpringApplication.run(SpringLeanApplication.class, args);
        Object serviceA = applicationContext.getBean("serviceA");
        ServiceA serviceA1 = (ServiceA) serviceA;
        serviceA1.test();
        Class[] classes = {testMapper.class};
        testMapper testMapper= (testMapper)Proxy.newProxyInstance(SpringLeanApplication.class.getClassLoader(), classes, (o1, method, objects) -> {
            Select annotation = method.getAnnotation(Select.class);
            if (!ObjectUtils.isEmpty(annotation)){
                String value = annotation.value()[0];
                System.out.println(value);
            }
            return o1;
        });
        testMapper.find(1111l);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
