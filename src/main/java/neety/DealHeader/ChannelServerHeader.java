package neety.DealHeader;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.nio.charset.StandardCharsets;

@ChannelHandler.Sharable
public class  ChannelServerHeader extends ChannelInboundHandlerAdapter {

    /**
     *
     * @param ctx  上下文对象。
     * @param msg   客户端发送过来的数据，默认为object
     *             读取完 完整数据以后触发
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        System.out.println("server ctx = "+ctx);
        System.out.println("客户端消息 " + ((ByteBuf)msg).toString(CharsetUtil.UTF_8));
        ctx.writeAndFlush(Unpooled.copiedBuffer("111".getBytes(StandardCharsets.UTF_8)))
                .addListener(ChannelFutureListener.CLOSE);
    }

    /**
     *  扫描完一次缓冲区以后触发
     * @param ctx
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.channel().close();
    }
}
