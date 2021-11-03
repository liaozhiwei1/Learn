package IO.RegisterCenter;

import IO.DealHeader.AcceptableHeader;
import IO.DealHeader.ReadableHeader;
import IO.DealHeader.impl.AcceptableHeaderImpl;
import IO.DealHeader.impl.ReaderHeaderImpl;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * All rights Reserved, Designed By www.baozun.com
 *
 * @title: Begin
 * @package: IO.RegisterCenter
 * @description: (用一句话描述该文件做什么)
 * @author: xugejun(giorno.xu @ baozun.com)
 * @date: 2021/11/3 10:36 上午
 * @version: v1.0
 * @copyright: 2021 www.baozun.com Inc. All rights reserved.
 * 注意：本内容仅限于上海宝尊电商内部传阅，禁止外泄以及用于其他的商业目的
 */
public class Begin {

    private static RegisterCenter registerCenter;


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        registerCenter = new RegisterCenter();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8081));
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
        while (true) {
            if (selector.selectNow()>0){
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey next = iterator.next();
                    if (next.isAcceptable()){
                        SocketChannel accept = serverSocketChannel.accept();
                        accept.configureBlocking(false);
                        accept.register(selector,SelectionKey.OP_READ);
                        iterator.remove();
                    }else if (next.isReadable()){
                        dealRequest(next);
                        next.channel().close();
                        iterator.remove();
                    }
                }
            }
        }
    }


    public static void dealRequest(SelectionKey accept) throws IOException {
        SocketChannel channel = (SocketChannel) accept.channel();
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        channel.read(allocate);
        allocate.flip();
        byte[] bytes = new byte[allocate.limit()];
        allocate.get(bytes);
        RegisterRequestDto registerRequestDto = JSONObject.parseObject(new String(bytes, StandardCharsets.UTF_8), RegisterRequestDto.class);
        String method = registerRequestDto.getMethodName();
        if ("register".equals(method)) {
            String serviceName = registerRequestDto.getServiceName();
            String ip = registerRequestDto.getIp();
            int prod = registerRequestDto.getProd();
            registerCenter.register(serviceName, new ServiceInfo(ip, prod));
        } else if ("getServiceInfo".equals(method)) {
            String serviceName = registerRequestDto.getServiceName();
            Set<ServiceInfo> serviceInfo = registerCenter.getServiceInfo(serviceName);
            allocate.compact();
            allocate.put(JSONObject.toJSONString(serviceInfo).getBytes());
            allocate.flip();
            channel.write(allocate);
        }

    }
}
