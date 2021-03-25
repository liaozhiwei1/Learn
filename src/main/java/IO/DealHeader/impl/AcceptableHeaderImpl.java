package IO.DealHeader.impl;

import IO.DealHeader.AcceptableHeader;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class AcceptableHeaderImpl implements AcceptableHeader {
    @Override
    public void invoke(ServerSocketChannel serverSocketChannel, Selector selector) throws IOException {
        SocketChannel accept = serverSocketChannel.accept();
        accept.configureBlocking(false);
        accept.register(selector, SelectionKey.OP_READ);
        System.out.println(accept.getRemoteAddress()+" 注册");
    }
}
