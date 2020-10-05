package com.immoc.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @Description:初始化，channel注册后，会执行里面的初始化方法
 */
public class HelloServerInitializer extends ChannelInitializer<SocketChannel> {
    //初始化Channel
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //通过SocketChannel 获得pipeline
        ChannelPipeline pipeline=  socketChannel.pipeline();
        //通过管道，添加handler
        //HttpServerCodec,自带的Handler 助手类，当请求到服务端做解码，响应到客户端做编码
        pipeline.addLast("HttpServerCodec", new HttpServerCodec());
        //添加自定义的助手类，返回hello netty
        pipeline.addLast("CustomerHandler", new CustomerHandler());
    }
}
