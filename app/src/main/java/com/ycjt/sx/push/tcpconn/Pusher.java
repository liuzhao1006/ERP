package com.ycjt.sx.push.tcpconn;

import com.ycjt.sx.push.StringUtil;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;


/**
 * 
 * @author 王玉凇
 * 
 */

public class Pusher {
	
	private int version = 1; 
	private int appId = 1;
	private int timeout;
	
	private String host;
	private int port ;
	private Socket socket;
	private InputStream in ;
	private OutputStream out ;
	
	public Pusher(String host, int port, int timeoutMillis, int version, int appId) throws Exception{
		this.setVersion(version);
		this.setAppId(appId);
		this.host = host;
		this.port = port;
		this.timeout = timeoutMillis;
		initSocket();
	}
	
	public Pusher(String host, int port, int timeoutMillis) throws Exception{
		this(host,port,timeoutMillis,1,1);
	}
	
	public Pusher(Socket socket)throws Exception{
		this.socket = socket;
		in = socket.getInputStream();
		out = socket.getOutputStream();
	}
	
	private void initSocket()throws Exception{
		socket = new Socket(this.host, this.port);
		socket.setSoTimeout(timeout);
		in = socket.getInputStream();
		out = socket.getOutputStream();
	}
	
	public void close() throws Exception{
		if(socket == null){
			return;
		}
		socket.close();
	}
	
	public void setVersion(int version) throws IllegalArgumentException{
		if(version < 1 || version > 255){
			throw new IllegalArgumentException("version must be 1 to 255");
		}
		this.version = version;
	}
	
	public int getVersion(){
		return this.version;
	}
	
	public void setAppId(int appId) throws IllegalArgumentException{
		if(appId < 1 || appId > 255){
			throw new IllegalArgumentException("appId must be 1 to 255");
		}
		this.appId = appId;
	}
	
	public int getAppId(){
		return this.appId;
	}
	
	private boolean checkUuidArray(byte[] uuid) throws IllegalArgumentException{
		if(uuid == null || uuid.length != 16){
			throw new IllegalArgumentException("uuid byte array must be not null and length of 16");
		}
		return true;
	}
	
	private boolean checkLongArray(byte[] longArray) throws IllegalArgumentException{
		if(longArray == null || longArray.length != 8){
			throw new IllegalArgumentException("array must be not null and length of 8");
		}
		return true;
	}
	
	public boolean push0x10Message(byte[] uuid) throws Exception{
		checkUuidArray(uuid);
		out.write(version);
		out.write(appId);
		out.write(16);
		out.write(uuid);
		out.write(0);
		out.write(0);
		out.flush();
		
		byte[] b = new byte[1];
		in.read(b);
		if((int)b[0] == 0){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean push0x11Message(byte[] uuid, long data) throws Exception{
		byte[] tmp = new byte[8];
		ByteBuffer.wrap(tmp).putLong(data);
		return this.push0x11Message(uuid, tmp);
	}
	
	public boolean push0x11Message(byte[] uuid, byte[] data) throws Exception{
		this.checkLongArray(data);
		out.write(version);
		out.write(appId);
		out.write(17);
		out.write(uuid);
		out.write(0);
		out.write(8);
		out.write(data);
		out.flush();
		
		byte[] b = new byte[1];
		in.read(b);
		if((int)b[0] == 0){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean push0x20Message(byte[] uuid, byte[] data) throws Exception{
		this.checkUuidArray(uuid);
		if(data == null){
			throw new NullPointerException("data array is null");
		}
		if(data.length == 0 || data.length > 4095){
			throw new IllegalArgumentException("data array length illegal, min 1, max 500");
		}
		byte[] dataLen = new byte[2];
		ByteBuffer.wrap(dataLen).putChar((char)data.length);
		out.write(version);
		out.write(appId);
		out.write(32);
		out.write(uuid);
		out.write(dataLen);
		
		out.write(data);
		out.flush();
		
		byte[] b = new byte[1];
		in.read(b);
		if((int)b[0] == 0){
			return true;
		}else{
			return false;
		}
		
	}
	
	public static void main(String[] args){
		Pusher pusher = null;
		try{
			boolean result;
			pusher = new Pusher("192.168.1.105",9999, 5000);
			String str = "st=>start: 开始|approved\nS1=>condition: 车辆通讯是否正常？|approved\nS2=>condition: 收发车网络中能否收\n到GPS状态？\n|approved\nS3=>operation: 打开202组合，更\n换GPS差分仪\n|approved\nS4=>condition: 故障是否排除?|approved\nS5=>operation: 启动远程支援|approved\nS6=>operation: 检查中心机后端的G\nPS信号\n|approved\nS7=>operation: 连接示波器|approved\nS8=>condition: 检查信号是否正常?|approved\nS9=>operation: 更换串口卡|approved\nS10=>condition: 故障是否排除?|approved\nE1=>end: 开始车辆通讯故障检\n查\n|approved\nE2=>end: 开始操控机故障检测|approved\nE3=>end: 结束|approved\nE4=>end: 其他检修流程|approved\nE5=>end: 结束|approved\nE6=>end: 其它检修流程|approved\n\nst->S1\nS1(yes)->S2\nS1(no,right)->E1\nS2(yes)->S3\nS2(no,right)->E2\nS3->S4\nS4(yes)->E3\nS4(no,right)->S5\nS5->S6\nS6->S7\nS7->S8\nS8(yes)->S9\nS8(no,right)->E4\nS9->S10\nS10(yes)->E5\nS10(no,right)->E6\n";
			result = pusher.push0x20Message(StringUtil.md5Byte("xiao"), str.getBytes("UTF-8"));
			
//			result = pusher.push0x20Message(StringUtil.hexStringToByteArray("2cb1abca847b4491bc2b206b592b64fd"), "cmd=ntfurl|title=通知标题|content=通知内容|tt=提示标题|url=/m/admin/eml/inbox/list".getBytes("UTF-8"));
			//result = pusher.push0x10Message(StringUtil.hexStringToByteArray("2cb1abca847b4491bc2b206b592b64fd"));
			//result = pusher.push0x11Message(StringUtil.hexStringToByteArray("2cb1abca847b4491bc2b206b592b64fd"),128);
			
			System.out.println(result);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(pusher != null){
				try{pusher.close();}catch(Exception e){};
			}
		}
	}

}
