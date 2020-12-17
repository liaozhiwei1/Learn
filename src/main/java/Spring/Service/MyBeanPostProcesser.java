/*
package Spring.Service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

*/
/**
 * @version: 1.0
 * @description:
 * @author: zhiwei.liao
 * @create: 2020-12-10 19:06:00
 **//*

@Component
public class MyBeanPostProcesser implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName);
        return bean;
    }
}
*/
