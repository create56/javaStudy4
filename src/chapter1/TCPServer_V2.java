package chapter1;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TCPServer_V2 {
	public static void main(String[] args) {
		ServerSocket serverSocket =null;
		try {
			serverSocket =new ServerSocket(7777);
			System.out.println(getTime() + "서버가 준비되었습니다");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		while (true) {
			try {
				System.out.println(getTime() + "연결요청을 기다립니다");
				
				// 서버가 클라이언트를 기다릴 시간을 설정
				// 이시간까지 기다리가 그이후에는 종료된다 
				// 5초(밀리초 단위로 시간을 설정하기 떄문에 초 * 1000)
				serverSocket.setSoTimeout(5*1000);
				Socket socket =serverSocket.accept();
				System.out.println(getTime() + socket.getInetAddress()+ "로부터 연결요청이 들어왔습니다");
				
				// 클라이언트의 port
				System.out.println("getPort() =" + socket.getPort());
				// 서버의 port
				System.out.println("getLoclaPort() =" + socket.getLocalPort());
				
				OutputStream out = socket.getOutputStream();
				DataOutputStream dos = new DataOutputStream(out);
				
				dos.writeUTF("[Notice] Test Message from Server.");
				System.out.println(getTime() + "클라이언트로 데이터를 전송합니다");
				
				dos.close();
				socket.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	private static String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]");
		return sdf.format(new Date());
		
	}

}
