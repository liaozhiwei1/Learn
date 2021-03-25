package IO.DealHeader;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public interface AcceptableHeader {

    void invoke(ServerSocketChannel serverSocketChannel , Selector selector) throws IOException;
}
