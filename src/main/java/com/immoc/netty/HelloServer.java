package com.immoc.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Description:实现客户端发送一个请求，服务器会返回一个hello netty
 */

public class HelloServer {
    public static void main(String[] args) throws Exception{
        //定义一对线程组
        //主线程组，用于客户端的连接，但不做任何处理
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        //从线程组，会把线程丢给他，让他从线程去做
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //netty服务器的创建，ServerBootstrap是一个启动类 strap 带子
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boosGroup, workerGroup) //设置主从服务器
                           .channel(NioServerSocketChannel.class) //设置nio双向通道
                           .childHandler(new HelloServerInitializer());//子处理器，用于处理workerGroup
            //启动server,并且设置8088为启动的端口号，同时启动方式为同步
            ChannelFuture channelFuture = serverBootstrap.bind(8088).sync();
            //关闭监听的channel,设置同步方式
            channelFuture.channel().closeFuture().sync();
        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
