package IO.RPCService.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
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

    public NIOService() throws IOException {
        this.selector = Selector.open();

    }

    public void register(String ip, int address) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel = SocketChannel.open();
        socketChannel.bind(new InetSocketAddress("127.0.0.1",8080));
        socketChannel.configureBlocking(false);
        socketChannel.register(selector,SelectionKey.OP_ACCEPT);
    }

    public void dealRequest() {
        while (start) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();
                try {
                    if (next.isAcceptable()){
                        next.channel().register(selector,SelectionKey.OP_READ);
                    }else if (next.isReadable()) {
                        //TODO 读事件处理
                    }
                } catch (ClosedChannelException e) {
                    e.printStackTrace();
                } finally {
                    iterator.remove();
                }

            }
        }
    }
}
