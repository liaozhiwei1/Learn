package IO.DealHeader;

import java.io.IOException;
import java.nio.channels.SelectionKey;

public interface ReadableHeader {
    void invoke(SelectionKey selectionKey) throws IOException;
}
