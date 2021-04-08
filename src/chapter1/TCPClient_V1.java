package chapter1;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

public class TCPClient_V1 {
	public static void main(String[] args) {
		try {
		// 루프백 주소
		String serverIp = "127.0.0.1";
//		String serverIp = "192.168.2.1";
		System.out.println("서버에 연결중입니다.서버 IP =" + serverIp );
			
			Socket socket = new Socket(serverIp,7777);
			InputStream in = socket.getInputStream();
			DataInputStream dis  = new DataInputStream(in);
			
			System.out.println("서버로 부터 받은 메서지 :" +dis.readUTF() );
			System.out.println("연결을 종료합니다");
			
			dis.close();
			socket.close();
			
			System.out.println("연결을 종료합니다");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
