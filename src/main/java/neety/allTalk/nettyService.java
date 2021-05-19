package neety.allTalk;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.local.LocalAddress;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * All rights Reserved, Designed By www.baozun.com
 *
 * @title: neetyService
 * @package: neety.allTalk
 * @description: (用一句话描述该文件做什么)
 * @author: xugejun(giorno.xu @ baozun.com)
 * @date: 2021/4/22 2:37 下午
 * @version: v1.0
 * @copyright: 2021 www.baozun.com Inc. All rights reserved.
 * 注意：本内容仅限于上海宝尊电商内部传阅，禁止外泄以及用于其他的商业目的
 */
public class nettyService {

    public static void main(String[] args) throws InterruptedException {


            EventLoopGroup bossEventLoop = new NioEventLoopGroup(1);
            EventLoopGroup workEventLoop = new NioEventLoopGroup();  //默认为 cpu核数*2
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossEventLoop,workEventLoop)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new MyChildHandlerContext());
                        }
                    });


            ChannelFuture sync = serverBootstrap.bind(6666).sync();

            sync.channel().closeFuture().sync();
        } finally {
            bossEventLoop.shutdownGracefully();
            workEventLoop.shutdownGracefully();
        }

    }
}
