package cn.lichengwu.test.netty.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * ChannelHandlerAdapter
 *
 * @author 佐井
 * @version 1.0
 * @created 2015-06-23 07:05
 */
public class TimeServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf data = ctx.alloc().buffer(4);
        data.writeInt((int) (System.currentTimeMillis()/1000));
        ChannelFuture future = ctx.writeAndFlush(data);
        future.addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
