package Spring.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * @version: 1.0
 * @description: \
 * @author: zhiwei.liao
 * @create: 2020-12-04 17:30:01
 **/
@Service
@ComponentScan(basePackages = "")
public class ServiceC {
    @Autowired
    public ServiceA serviceA;


    public ServiceC() {
        System.out.println("serviceC init（）");
    }

}
