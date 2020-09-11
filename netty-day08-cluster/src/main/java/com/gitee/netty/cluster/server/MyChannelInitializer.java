package com.gitee.netty.cluster.server;

import com.gitee.netty.cluster.utils.ExtServerService;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;


/**
 * @author jie
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    private ExtServerService extServerService;

    public MyChannelInitializer(ExtServerService extServerService) {
        this.extServerService = extServerService;
    }

    @Override
    protected void initChannel(SocketChannel channel) {
        // 基于换行符号
        channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        // 解码转String，注意调整自己的编码格式GBK、UTF-8
        channel.pipeline().addLast(new StringDecoder(Charset.forName("UTF-8")));
        // 解码转String，注意调整自己的编码格式GBK、UTF-8
        channel.pipeline().addLast(new StringEncoder(Charset.forName("UTF-8")));
        // 在管道中添加我们自己的接收数据实现方法
        channel.pipeline().addLast(new MyServerHandler(extServerService));
    }

}
