package chapter1;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

public class TCPClient_V1 {
	public static void main(String[] args) {
		try {
		// ������ �ּ�
		String serverIp = "127.0.0.1";
//		String serverIp = "192.168.2.1";
		System.out.println("������ �������Դϴ�.���� IP =" + serverIp );
			
			Socket socket = new Socket(serverIp,7777);
			InputStream in = socket.getInputStream();
			DataInputStream dis  = new DataInputStream(in);
			
			System.out.println("������ ���� ���� �޼��� :" +dis.readUTF() );
			System.out.println("������ �����մϴ�");
			
			dis.close();
			socket.close();
			
			System.out.println("������ �����մϴ�");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
