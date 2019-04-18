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
    private final int port;
    public NettyDemoServer(int port){
        this.port=port;
    }

    /**
     * @Description:  to start netty server
     * @Date 2019/4/18 9:52
     * @Author rtj599
     **/
    public void start() throws  Exception{
        final NettyDemoServerHandler handler=new NettyDemoServerHandler();
        //accept and handle new connections
        EventLoopGroup group=new NioEventLoopGroup();
        //bootstrap a channel to use for client
        ServerBootstrap bootstrap =new ServerBootstrap();
        try{
            bootstrap.group(group)
                    //use NioServerSocketChannel as Channel
                    .channel(NioServerSocketChannel.class)
                    //bind port
                    .localAddress(new InetSocketAddress(port))
                    //when a new connection is accepted,a new child socket channel will be created
                    //and be put into channel pipeline,then bind the channel handler
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline().addLast(handler);
                        }
                    });
            //bind server async, blocking until bind finished because sync() is called
            ChannelFuture future=bootstrap.bind().sync();
            //close channel, blocking until close finished because sync() is called
            future.channel().closeFuture().sync();
        }
        finally {
            //close EventLoopGroup  and release all resources
            group.shutdownGracefully().sync();
        }
    }
}
