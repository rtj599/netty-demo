package com.rtj599.server;

import com.rtj599.handler.NettyDemoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.net.InetSocketAddress;

public class NettyDemoServer {
    private final int PORT;
    public NettyDemoServer(int port){
        PORT=port;
    }
    public static void main(String[]args)throws Exception{
        int port =8848;
        new NettyDemoServer(port).start();
    }

    public void start() throws  Exception{
        final NettyDemoServerHandler handler=new NettyDemoServerHandler();
        EventLoopGroup group=new NioEventLoopGroup();
        ServerBootstrap bootstrap =new ServerBootstrap();
        try{
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(PORT))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline().addLast(handler);
                        }
                    });
            ChannelFuture future=bootstrap.bind().sync();
            future.channel().closeFuture().sync();
        }
        finally {
            group.shutdownGracefully().sync();
        }
    }
}
