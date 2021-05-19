package neety.allTalk;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.util.concurrent.EventExecutor;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * All rights Reserved, Designed By www.baozun.com
 *
 * @title: MyChildHandlerContext
 * @package: neety.allTalk
 * @description: (用一句话描述该文件做什么)
 * @author: xugejun(giorno.xu @ baozun.com)
 * @date: 2021/4/22 2:50 下午
 * @version: v1.0
 * @copyright: 2021 www.baozun.com Inc. All rights reserved.
 * 注意：本内容仅限于上海宝尊电商内部传阅，禁止外泄以及用于其他的商业目的
 */
public class MyChildHandlerContext extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

        String s = ((ByteBuf) msg).toString(StandardCharsets.UTF_8);
//        List<String> names = ctx.pipeline().channel().eventLoop().parent().
        /*names.stream().peek(item->{
            ChannelHandlerContext context = ctx.pipeline().context(item);
            if (item.hashCode()!=ctx.hashCode()){
                context.writeAndFlush(s);
            }
        });*/
//        ctx.writeAndFlush(Unpooled.copiedBuffer("群发完成".getBytes(StandardCharsets.UTF_8)));
    }
}
