package chapter1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TCPServer_V1 {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		
		try {
			// cmd프로그램을 사용해서 실행 // 마우스 우클릭을 사용해서 경로을 복사 붙어넣기
			// 클라이언트의 응답을 받아들일 소켓
			// 7777포트를 사용한 서버 소켓 생성
			serverSocket = new ServerSocket(7777);
			System.out.println(getTime() + "서버가 준비되었습니다");
		} catch (Exception e) {
			e.printStackTrace();
		}
		while (true) {
			try {
				System.out.println(getTime() + "연결요청을 기다립니다");
				
				Socket socket = serverSocket.accept();
				System.out.println(getTime() + socket.getInetAddress()+ "로부터 연결요청이 들어왔습니다");
				
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

	private static String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]");
		return sdf.format(new Date());
	}

}
