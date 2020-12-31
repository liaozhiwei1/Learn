package Spring.Service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @version: 1.0
 * @description:
 * @author: zhiwei.liao
 * @create: 2020-12-08 20:54:10
 **/
@Configuration
public class LiaoBeanFactoryPostProcess implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition serviceA = beanFactory.getBeanDefinition("serviceA");
        System.out.println(serviceA.getScope());
        serviceA.setParentName("service");
//        beanFactory.getBean("serviceA");
//        serviceA.setScope("SCOPE_SINGLETON");
    }
}
