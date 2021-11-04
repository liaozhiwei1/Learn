package neety;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class neetyClient {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup eventExecutors = null;
        try {
             eventExecutors=new NioEventLoopGroup();  //线程组

            Bootstrap bootstrap = new Bootstrap();   //客户端启动必备

            bootstrap.group(eventExecutors)
                    .channel(NioSocketChannel.class) //指定网络传输
                    .remoteAddress(new InetSocketAddress("127.0.0.1",6666))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addFirst(new ClientHandle()); ///加入自己的处理器
                        }
                    });
            System.out.println("客户端ok");

            /*
            连接到远程节点，并阻塞直到连接完成。
             */
            ChannelFuture sync = bootstrap.connect().sync();
            sync.channel().closeFuture().sync();


        } finally {
            assert eventExecutors != null;
            eventExecutors.shutdownGracefully().sync();
        }
    }
}

class ClientHandle extends SimpleChannelInboundHandler<ByteBuf> {

    //客户端读到数据以后就会执行
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) {
        System.out.println("client accept:"+msg.toString(StandardCharsets.UTF_8));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello netty".getBytes(StandardCharsets.UTF_8)));
        super.channelActive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
