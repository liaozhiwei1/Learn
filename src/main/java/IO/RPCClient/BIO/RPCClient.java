package IO.RPCClient.BIO;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * All rights Reserved, Designed By www.baozun.com
 *
 * @title: RPCClient
 * @package: IO
 * @description: (用一句话描述该文件做什么)
 * @author: xugejun(giorno.xu @ baozun.com)
 * @date: 2021/9/17 11:20 上午
 * @version: v1.0
 * @copyright: 2021 www.baozun.com Inc. All rights reserved.
 * 注意：本内容仅限于上海宝尊电商内部传阅，禁止外泄以及用于其他的商业目的
 */
public class RPCClient {

    public static <T> T getRemoteProxyObj(final Class<?> serviceInterface,
                                          String hostname, int port) {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(hostname, port);
        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(),
                new Class<?>[]{serviceInterface}
                , new ReallyProxy(serviceInterface, inetSocketAddress));
    }

}

class ReallyProxy implements InvocationHandler {

    private Class<?> serviceInfo;
    private InetSocketAddress inetSocketAddress;

    public ReallyProxy(Class<?> serviceInfo, InetSocketAddress inetSocketAddress) {
        this.serviceInfo = serviceInfo;
        this.inetSocketAddress = inetSocketAddress;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Socket socket = null;
        ObjectOutputStream output = null;
        ObjectInputStream input = null;

        try{
            socket = new Socket();
            socket.connect(inetSocketAddress);

            output = new ObjectOutputStream(socket.getOutputStream());
            output.writeUTF(serviceInfo.getSimpleName());//方法所在的类
            output.writeUTF(method.getName());//方法的名
            output.writeObject(method.getParameterTypes());//方法的入参类型
            output.writeObject(args);
            output.flush();

            input = new ObjectInputStream(socket.getInputStream());
            return input.readObject();

        }finally{
            if (socket!=null) socket.close();
            if (output!=null) output.close();
            if (input!=null) input.close();
        }

    }
}
