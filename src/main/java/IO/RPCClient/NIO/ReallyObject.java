package IO.RPCClient.NIO;

import IO.RPCClient.RPCEntry;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * All rights Reserved, Designed By www.baozun.com
 *
 * @title: ReallyObject
 * @package: IO.RPCClient.NIO
 * @description: (用一句话描述该文件做什么)
 * @author: xugejun(giorno.xu @ baozun.com)
 * @date: 2021/9/17 5:52 下午
 * @version: v1.0
 * @copyright: 2021 www.baozun.com Inc. All rights reserved.
 * 注意：本内容仅限于上海宝尊电商内部传阅，禁止外泄以及用于其他的商业目的
 */
public class ReallyObject implements InvocationHandler {
    private String ip;
    private int address;
    private NIOSocket nioSocket;
    private Class<?> classInfo;

    public ReallyObject(String ip, int address,NIOSocket nioSocket,Class<?> classInfo) {
        this.ip = ip;
        this.address = address;
        this.nioSocket = nioSocket;
        this.classInfo = classInfo;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        SocketChannel connect = nioSocket.connect("127.0.0.1", 8080);
        RPCEntry rpcEntry = new RPCEntry(classInfo.getSimpleName(),method.getName(),method.getParameterTypes(),args);
        nioSocket.dealRequest(rpcEntry);
        return null;
    }
}
