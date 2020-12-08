package Spring.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version: 1.0
 * @description:
 * @author: zhiwei.liao
 * @create: 2020-12-04 17:30:17
 **/
@Service
public class ServiceB {

    @Autowired
    public ServiceA serviceA;

    public ServiceB() {
        System.out.println("serviceB init（）");
    }
}
