package IO.SamplReator;

import IO.DealHeader.AcceptableHeader;
import IO.DealHeader.ReadableHeader;
import IO.DealHeader.impl.AcceptableHeaderImpl;
import IO.DealHeader.impl.ReaderHeaderImpl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * Reator模式提供接口处理
 * 1.单Reator 单线程：reator和处理业务是同一线程 （一层）
 * 2.单Reator 多线程：reator是一个线程处理业务逻辑交由线程池 （两层）
 * 3.主从Reator 多线程：主Reator只处理连接请求，从Reator处理读写请求（多线程） 多线程处理业务  （三层）
 * 4.netty主要基于主从Reator多线程模型
 */
public class sampReator {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(6006));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
        //TODO 可以使用工厂方法，或者让使用者传入。
        AcceptableHeader acceptableHeader = new AcceptableHeaderImpl();
        ReadableHeader readableHeader = new ReaderHeaderImpl();
        while (true){
            if (selector.selectNow()>0){
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                while (iterator.hasNext()){
                    SelectionKey next = iterator.next();
                    try {
                        if (next.isAcceptable()) {
                            //TODO 链接请求处理
                            acceptableHeader.invoke(serverSocketChannel, selector);
                        } else if (next.isReadable()) {
                            //TODO 读数据请求处理
                            readableHeader.invoke(next);
                            next.channel().close();
                        } else if (next.isWritable()) {
                            //TODO 写数据请求处
                        }
                    }finally {
                        iterator.remove();
                    }
                }

            }
        }
    }
}
