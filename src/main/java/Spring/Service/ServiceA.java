package Spring.Service;

import MyBatis.testMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version: 1.0
 * @description: \
 * @author: zhiwei.liao
 * @create: 2020-12-04 17:30:01
 **/
@Service
public class ServiceA {
//    @Autowired
//    public ServiceB serviceB;

    @Autowired
    public testMapper testMapper;

    public ServiceA() {
        System.out.println("serviceA init（）");
    }


    public void test(){
        System.out.println("test");
    }

}
