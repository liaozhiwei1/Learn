package IO.RPCClient.NIO;

import IO.RPCClient.RPCEntry;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * All rights Reserved, Designed By www.baozun.com
 *
 * @title: NIOSocket
 * @package: IO.RPCClient.NIO
 * @description: (用一句话描述该文件做什么)
 * @author: xugejun(giorno.xu @ baozun.com)
 * @date: 2021/9/17 5:55 下午
 * @version: v1.0
 * @copyright: 2021 www.baozun.com Inc. All rights reserved.
 * 注意：本内容仅限于上海宝尊电商内部传阅，禁止外泄以及用于其他的商业目的
 */
public class NIOSocket {

    private Selector selector;
    private boolean start = false;

    public NIOSocket() throws IOException {
        this.selector = Selector.open();
    }

    public SocketChannel connect(String ip, int address) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress(ip, address));
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        return socketChannel;
    }

    public void dealRequest(RPCEntry rpcEntry) {
        while (start) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();
                try {
                    if (next.isConnectable()) {
                        SelectableChannel channel = next.channel();
                        channel.register(selector, SelectionKey.OP_READ);

                    } else if (next.isReadable()) {
                        //TODO 读事件处理
                    } else if (next.isWritable()) {
                        //TODO 写事件处理
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
