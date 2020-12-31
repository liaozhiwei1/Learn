package MySpring.Test.service;


import MySpring.Spring.Component;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @version: 1.0
 * @description:
 * @author: zhiwei.liao
 * @create: 2020-12-30 13:43:51
 **/
@Component
public class ServiceA {
    @Autowired
    private ServiceB serviceB;
}
