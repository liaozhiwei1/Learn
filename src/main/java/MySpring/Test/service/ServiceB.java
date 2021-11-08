package MySpring.Test.service;

import MySpring.Spring.Component;
import MySpring.Spring.Scope;

/**
 * @version: 1.0
 * @description:
 * @author: zhiwei.liao
 * @create: 2020-12-30 13:43:58
 **/
@Component("serviceB")
public class ServiceB {

    public void test(){
        System.out.println("success");
    }
}
