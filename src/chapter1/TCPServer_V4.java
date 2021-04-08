package chapter1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TCPServer_V4 implements Runnable{
	ServerSocket serverSocket;
	Thread[] threadArr;
	
	public TCPServer_V4(int num) {
		try {
			serverSocket = new ServerSocket(7777);
			System.out.println(getTime() + "서버가 준비되었습니다.");
			
			threadArr = new Thread[num];
			
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	@Override
	public void run() {
		while (true) {
			try {
				System.out.println(getTime() + Thread.currentThread() + "가 연결요청을 기다립니다");
				
				Socket socket = serverSocket.accept();
				System.out.println(getTime() + socket.getInetAddress() + "로부터 연결요청이 들어왔습니다");
				
				OutputStream out = socket.getOutputStream();
				DataOutputStream dos = new DataOutputStream(out);
				dos.writeUTF("[Notice] Test Message from Server.");
				System.out.println(getTime() + "클라이언트로 데이터를 전송합니다");
				
				dos.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void start() {
		for (int i = 0; i < threadArr.length; i++) {
			threadArr[i] = new Thread(this);
			threadArr[i].start();
			
		}
	}
	
	public static void main(String[] args) {
		TCPServer_V4 server = new TCPServer_V4(5);
		
		server.start();
		

	}
	private String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]");
		return sdf.format(new Date());	
	
	}

}
