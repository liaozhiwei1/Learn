package Spring.Aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @version: 1.0
 * @description:
 * @author: zhiwei.liao
 * @create: 2020-12-10 16:47:55
 **/
@Aspect
@Component
public class LogAop {

    @Before("execution(public * Spring.Service.ServiceA.*(..))")
    public void doAccessCheck() {
        System.out.println("[Before] do access check...");
    }
}
