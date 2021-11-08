package MySpring.Test.service;


import MySpring.Spring.Autowired;
import MySpring.Spring.Component;

/**
 * @version: 1.0
 * @description:
 * @author: zhiwei.liao
 * @create: 2020-12-30 13:43:51
 **/
@Component("serviceA")
public class ServiceA {

    @Autowired
    private ServiceB serviceB;


    public void test(){
        serviceB.test();
    }
}
