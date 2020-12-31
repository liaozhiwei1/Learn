package Spring;


import Spring.Service.ServiceA;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringLeanApplication implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    public static void main(String[] args) {

        SpringApplication.run(SpringLeanApplication.class, args);
        Object serviceA = applicationContext.getBean("serviceA");
        ServiceA serviceA1 = (ServiceA) serviceA;
        serviceA1.test();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
