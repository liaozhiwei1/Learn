package neety.allTalk;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.StandardCharsets;

public class neetyClient {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup eventExecutors = null;
        try {
             eventExecutors=new NioEventLoopGroup();

            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(eventExecutors)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    ctx.writeAndFlush(Unpooled.copiedBuffer("saaa".getBytes(StandardCharsets.UTF_8)));
                                }

                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    ByteBuf m = (ByteBuf) msg;
                                    System.out.println(m.toString(StandardCharsets.UTF_8));
                                }
                            });  ///加入自己的处理器
                        }
                    });
            System.out.println("客户端ok");

            ChannelFuture sync = bootstrap.connect("127.0.0.1", 6666).sync();

            sync.channel().closeFuture().sync();
        } finally {
            assert eventExecutors != null;
            eventExecutors.shutdownGracefully();
        }
    }
}
