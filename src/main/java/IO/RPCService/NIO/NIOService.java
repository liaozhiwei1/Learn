package IO.RPCService.NIO;

import IO.RPCService.BIO.RPCService;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * All rights Reserved, Designed By www.baozun.com
 *
 * @title: NIOService
 * @package: IO.RPCService.NIO
 * @description: (用一句话描述该文件做什么)
 * @author: xugejun(giorno.xu @ baozun.com)
 * @date: 2021/9/17 6:11 下午
 * @version: v1.0
 * @copyright: 2021 www.baozun.com Inc. All rights reserved.
 * 注意：本内容仅限于上海宝尊电商内部传阅，禁止外泄以及用于其他的商业目的
 */
public class NIOService {


    private Selector selector;
    private boolean start = false;

    private ServerSocketChannel serverSocketChannel;

    public static void main(String[] args) throws IOException {
        RPCService.register("BusinessService","127.0.0.1",8079);
        NIOService rpcService = new NIOService();
        rpcService.register();
        rpcService.dealRequest();
    }

    public void register() throws IOException {
        serverSocketChannel = java.nio.channels.ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1",8079));
        serverSocketChannel.configureBlocking(false);
        start = true;
        selector = Selector.open();
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
    }

    public void dealRequest() {
        while (start) {
            try {
                if (selector.selectNow() > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey next = iterator.next();
                        try {
                            if (next.isAcceptable()) {
                                SocketChannel accept = serverSocketChannel.accept();
                                accept.configureBlocking(false);
                                accept.register(selector,SelectionKey.OP_READ);
                                iterator.remove();
                            } else if (next.isReadable()) {
                                SocketChannel channel = (SocketChannel) next.channel();
                                new Really(channel).run();
                                iterator.remove();
                            }
                        } catch (ClosedChannelException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class Really implements Runnable{

        private SocketChannel channel;

        public Really(SocketChannel channel) {
            this.channel = channel;
        }

        @Override
        public void run() {
            ByteBuffer allocate = ByteBuffer.allocate(1024);
            try {
                channel.read(allocate);
                allocate.flip();
                byte[] bytes = new byte[allocate.limit()];
                allocate.get(bytes);
                RequestDto requestDto = JSONObject.parseObject(new String(bytes, StandardCharsets.UTF_8), RequestDto.class);
                Class<?> aClass = Class.forName(requestDto.getClassName());
                Object o = aClass.newInstance();
                Method method = aClass.getMethod(requestDto.getMethod(), requestDto.getArgsType());
                method.invoke(o,requestDto.getArgs());
                channel.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
