package com.lk.netty.server.coder;

import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lk.netty.server.packet.AbstractPacket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 *  自定义编码器
 * 2019年3月11日
 * likai
 */
public class PacketEncoder extends MessageToByteEncoder<AbstractPacket> {
	
	//日志
	private Logger logger = LoggerFactory.getLogger(PacketEncoder.class);

	@Override
	protected void encode(ChannelHandlerContext ctx, AbstractPacket msg, ByteBuf out)
			throws Exception {
		try {
			_encode(ctx, msg, out);
		}catch(Exception e) {
			logger.error("", e);
		}
	}

	private void _encode(ChannelHandlerContext ctx, AbstractPacket msg, ByteBuf out) throws Exception {
		out.writeInt(msg.getPacketType().getType());	//消息头
		//消息体
		if(msg.isUseCompression()){  //开启gzip压缩
			ByteBuf buf =  Unpooled.buffer();
			msg.writeBody(buf);
			byte[] content = new byte[buf.readableBytes()];
			buf.getBytes(0, content);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(bos);
			gzip.write(content);
			gzip.close();
			byte[] destBytes = bos.toByteArray();
			out.writeInt(destBytes.length);
			out.writeBytes(destBytes);
			bos.close();
		}else{
			msg.writeBody(out);
		}
	}
}
