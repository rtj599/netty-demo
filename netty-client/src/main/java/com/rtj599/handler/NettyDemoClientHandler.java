package com.rtj599.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
* @Description:  to handle channel events
* @Date 2019/4/18 9:21
 * @Author rtj599
**/
@ChannelHandler.Sharable //can be safely used by channels at the same time
public class NettyDemoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("oh yeah，the deep dark fantasies",CharsetUtil.UTF_8));
//        ctx.fireChannelActive();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("client received:"+msg.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
//        ctx.fireExceptionCaught(cause);
        cause.printStackTrace();
        ctx.close();
    }
}

