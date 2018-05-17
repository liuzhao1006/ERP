package com.ycjt.sx.push.tcpconn;

import java.net.SocketAddress;
import java.nio.ByteBuffer;

/**
 * 
 * @author 王玉凇
 * 消息推送Message类
 */
public final class PusherMessage {
	
	public static int version = 1;
	public static final int SERVER_MESSAGE_MIN_LENGTH = 5;
	public static final int CLIENT_MESSAGE_MIN_LENGTH = 21;
	public static final int CMD_0x00 = 0;//心跳包
	public static final int CMD_0x10 = 16;//通用信息
	public static final int CMD_0x11 = 17;//分类信息
	public static final int CMD_0x20 = 32;//自定义信息
	
	protected SocketAddress address;
	protected byte[] data;
	
	public PusherMessage(SocketAddress address, byte[] data){
		this.address = address;
		this.data = data;
	}
	
	public int getContentLength(){
		return (int)ByteBuffer.wrap(data, SERVER_MESSAGE_MIN_LENGTH - 2, 2).getChar();
	}
	
	public int getCmd(){
		byte b = data[2];
		return b & 0xff;
	}
	
	public boolean checkFormat(){
		if(address == null || data == null || data.length < PusherMessage.SERVER_MESSAGE_MIN_LENGTH){
			return false;
		}
		int cmd = getCmd();
		if(cmd != CMD_0x00
				&& cmd != CMD_0x10
				&& cmd != CMD_0x11
				&& cmd != CMD_0x20){
			return false;
		}
		int dataLen = getContentLength();
		if(data.length != dataLen + SERVER_MESSAGE_MIN_LENGTH){
			return false;
		}
		if(cmd ==  CMD_0x10 && dataLen != 0){
			return false;
		}
		
		if(cmd ==  CMD_0x11 && dataLen != 8){
			return false;
		}
		
		if(cmd ==  CMD_0x20 && dataLen < 1){//must has content
			return false;
		}
		return true;
	}
	
	public void setData(byte[] data){
		this.data = data;
	}
	
	public byte[] getData(){
		return this.data;
	}
	
	public void setSocketAddress(SocketAddress address){
		this.address = address;
	}
	
	public SocketAddress getSocketAddress(){
		return this.address;
	}
	
	public static void setVersion(int v){
		if(v < 1 || v > 255){
			return;
		}
		version = v;
	}
	
	public static int getVersion(){
		return version;
	}

}
