package com.rtj599;

import com.rtj599.client.NettyDemoClient;

public class NettyDemoClientApplication {
    public static void main(String args[]) throws Exception{
        String host="127.0.0.1";
        int port=8848;
        new NettyDemoClient(host,port).start();
    }
}
