package IO.RPCClient.NIO;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * All rights Reserved, Designed By www.baozun.com
 *
 * @title: RPCNIOClient
 * @package: IO.RPCClient.NIO
 * @description: (用一句话描述该文件做什么)
 * @author: xugejun(giorno.xu @ baozun.com)
 * @date: 2021/9/17 5:47 下午
 * @version: v1.0
 * @copyright: 2021 www.baozun.com Inc. All rights reserved.
 * 注意：本内容仅限于上海宝尊电商内部传阅，禁止外泄以及用于其他的商业目的
 */
public class RPCNIOClient {

    private <T> T getProxy(Class<?> serviceInfo,String ip,int address,NIOSocket nioSocket) {
        return (T) Proxy.newProxyInstance(serviceInfo.getClassLoader(),new Class[]{serviceInfo},new ReallyObject(ip,address,nioSocket,serviceInfo));
    }
}
