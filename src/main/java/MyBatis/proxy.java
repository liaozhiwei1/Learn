package MyBatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * All rights Reserved, Designed By www.baozun.com
 *
 * @title: proxy
 * @package: MyBatis
 * @description: (用一句话描述该文件做什么)
 * @author: xugejun(giorno.xu @ baozun.com)
 * @date: 2021/9/26 3:16 下午
 * @version: v1.0
 * @copyright: 2021 www.baozun.com Inc. All rights reserved.
 * 注意：本内容仅限于上海宝尊电商内部传阅，禁止外泄以及用于其他的商业目的
 */
public class proxy {


    public static void main(String[] args) {
        c c = new c();
        a proxy = getProxy(a.class,c);
        String d = proxy.d("a");
        System.out.println(d);
    }

    public static <T> T getProxy(Class<T> clazz,a a){
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new b(a));
    }
}

interface  a{
    String d(String x);
}

class c implements a{

    @Override
    public String d(String x) {
        return x;
    }
}

class b implements InvocationHandler {

    public a a;

    public b(MyBatis.a a) {
        this.a = a;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        //TODO 前置增强
        System.out.println("前置增强");
        String d = a.d((String) args[0]);
        //TODO 后置增强
        return d;
    }
}