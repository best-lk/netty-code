package com.lk.netty.client.packet.coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;

import com.lk.netty.client.packet.AbstractPacket;
import com.lk.netty.client.packet.PacketManager;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * ������ 2019��3��8�� likai
 */
public class PacketDecoder extends LengthFieldBasedFrameDecoder {

	public PacketDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment,
			int initialBytesToStrip) {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
	}

	public Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
		ByteBuf frame = (ByteBuf) super.decode(ctx, in);
		if (frame.readableBytes() <= 0)
			return null;

		int packetType = frame.readInt();
		AbstractPacket packet = PacketManager.INSTANCE.createNewPacket(packetType);
		boolean useCompression = packet.isUseCompression();
		ByteBuf realBuf = decompression(frame, useCompression);
		packet.readBody(realBuf);

		return packet;
	}

	private ByteBuf decompression(ByteBuf sourceBuf, boolean useCompression) throws Exception {
		if (!useCompression) {
			return sourceBuf;
		}

		int bodyLength = sourceBuf.readInt();// �ȶ�ѹ�����ݵĳ���
		byte[] sourceBytes = new byte[bodyLength];
		sourceBuf.readBytes(sourceBytes);// �õ�ѹ�����ݵ��ֽ�����

        //��ѹ��
		ByteArrayInputStream bis = new ByteArrayInputStream(sourceBytes);
		GZIPInputStream gzip = new GZIPInputStream(bis);

		final int MAX_MSG_LENGTH = bodyLength * 2; // ����ѹ�������Ϊ100%����������
		byte[] content = new byte[MAX_MSG_LENGTH];
		int num = -1;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		while ((num = gzip.read(content, 0, content.length)) != -1) {
			baos.write(content, 0, num);
		}
		baos.flush();
		gzip.close();
		bis.close();

        //���·�װ��ByteBuf����
		ByteBuf resultBuf = Unpooled.buffer();
		byte[] realBytes = baos.toByteArray(); // ѹ��ǰ��ʵ������
		resultBuf.writeBytes(realBytes);
		baos.close();

		return resultBuf;
	}
}
