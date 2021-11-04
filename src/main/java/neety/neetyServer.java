package neety;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import neety.DealHeader.ChannelServerHeader;

import java.util.HashMap;

public class neetyServer {

    public static void main(String[] args) throws InterruptedException {

        final ChannelServerHeader channelServerHeader = new ChannelServerHeader();

        ServerBootstrap b = new ServerBootstrap();
        //bossGroup只是处理连接请求  真正的和客户端业务处理，会交给workGroup完成处理
        //两个都是无限循环
        //bossGroup和workGroup含有的子线程数默认为cpu核数*2
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(4);
//        NioEventLoopGroup workerGroup = new NioEventLoopGroup(12);
        try {
            b.group(bossGroup)  //设置两个线程组
                    .channel(NioServerSocketChannel.class)  //设置使用NioServerSocketChannel为通道实现
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .localAddress(6666)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(channelServerHeader);  //加入处理header
                        }
                    });
            //开始子线程处理
            ChannelFuture sync = b.bind().sync();

            //监听通道关闭事件
            sync.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully().sync();
        }

    }
}
