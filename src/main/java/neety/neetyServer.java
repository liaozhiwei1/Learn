package neety;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import neety.DealHeader.ChannelServerHeader;

public class neetyServer {

    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap b = new ServerBootstrap();

        //bossGroup只是处理连接请求  真正的和客户端业务处理，会交给workGroup完成处理
        //两个都是无限循环
        //bossGroup和workGroup含有的子线程数默认为cpu核数*2
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(4);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(12);
        try {
            b.group(bossGroup, workerGroup)  //设置两个线程组
                    .channel(NioServerSocketChannel.class)  //设置使用NioServerSocketChannel为通道实现
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ChannelServerHeader());  //加入处理header
                        }
                    });

            //开始子线程处理
            ChannelFuture sync = b.bind(6666).sync();

            //监听通道关闭时间
            sync.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
