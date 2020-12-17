package Spring;

import org.springframework.stereotype.Service;

/**
 * @version: 1.0
 * @description:
 * @author: zhiwei.liao
 * @create: 2020-12-02 10:14:32
 **/
@Service
public class service {

    public int div(int x, int y) {
        int res = x / y;
        Class<service> serviceClass = service.class;
        System.out.println("=============service 执行 div 结果为" + res);
        return res;
    }
}
