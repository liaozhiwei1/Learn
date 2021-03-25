package IO.DealHeader.impl;

import IO.DealHeader.ReadableHeader;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class ReaderHeaderImpl implements ReadableHeader {
    @Override
    public void invoke(SelectionKey selectionKey) throws IOException {
        SocketChannel channel = (SocketChannel) selectionKey.channel();
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        channel.read(allocate);
        allocate.flip();
        byte[] array = allocate.array();
        //TODO  后续处理逻辑
        System.out.println(new String(array));

    }
}
