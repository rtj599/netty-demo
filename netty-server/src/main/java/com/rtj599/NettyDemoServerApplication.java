package com.rtj599;

import com.rtj599.server.NettyDemoServer;

public class NettyDemoServerApplication {

    public static void main(String[]args)throws Exception{
        int port =8848;
        new NettyDemoServer(port).start();
    }
}
