package com.rtj599.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;


/**
* @Description:  to handle channel events
* @Date 2019/4/18 9:21
 * @Author rtj599
**/
@ChannelHandler.Sharable //can be safely used by channels at the same time
public class NettyDemoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel registed");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel unregistered");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel active");
        super.channelActive(ctx);
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel inactive");
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf=(ByteBuf)msg;
        System.out.println("[NettyDemoServerHandler.channelRead]:"+buf.toString(CharsetUtil.UTF_8));
        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel read complete");
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
//        ctx.fireChannelReadComplete();
    }

    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("user event triggered");
        super.userEventTriggered(ctx,evt);
    }

    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel writability changed");
        ctx.fireChannelWritabilityChanged();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();

//        ctx.fireExceptionCaught(cause);
    }



}

