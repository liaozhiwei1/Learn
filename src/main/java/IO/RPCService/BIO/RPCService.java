package IO.RPCService.BIO;

import IO.BusinessService;

import java.io.*;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * All rights Reserved, Designed By www.baozun.com
 *
 * @title: RPCService
 * @package: IO
 * @description: (用一句话描述该文件做什么)
 * @author: xugejun(giorno.xu @ baozun.com)
 * @date: 2021/9/17 10:31 上午
 * @version: v1.0
 * @copyright: 2021 www.baozun.com Inc. All rights reserved.
 * 注意：本内容仅限于上海宝尊电商内部传阅，禁止外泄以及用于其他的商业目的
 */
public class  RPCService {
    private static final String CLASSNAME = "className";
    private static final String METHOD = "method";
    ServerSocket socket;

    private Map<String, Class> map = new ConcurrentHashMap<>();

    public RPCService() throws IOException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress( 8080);
        this.socket = new ServerSocket();
        socket.bind(inetSocketAddress);
    }

    public void deal() {
        while (true) {
            Socket accept = null;
            try {
                accept = socket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.doDeal(accept);
        }
    }


    public void doDeal(Socket socket) {
        try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())) {
            String className = inputStream.readUTF();
            String method = inputStream.readUTF();
            Class[] argsType = (Class[]) inputStream.readObject();
            Object[] args = (Object[]) inputStream.readObject();
            Class aClass = map.get(className);
            Method method1 = aClass.getMethod(method, argsType);
            Object invoke = method1.invoke(aClass.newInstance(), args);
            outputStream.writeObject(invoke);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void register(String className, Class clazz) {
        map.put(className, clazz);
    }

    public static void main(String[] args) throws IOException {
        RPCService rpcService = new RPCService();
        rpcService.register(BusinessService.class.getSimpleName(), BusinessService.class);
        new Thread(() -> rpcService.deal()).start();
    }
}
