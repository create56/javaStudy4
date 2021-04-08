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
			System.out.println(getTime() + "������ �غ�Ǿ����ϴ�");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		while (true) {
			try {
				System.out.println(getTime() + "�����û�� ��ٸ��ϴ�");
				
				// ������ Ŭ���̾�Ʈ�� ��ٸ� �ð��� ����
				// �̽ð����� ��ٸ��� �����Ŀ��� ����ȴ� 
				// 5��(�и��� ������ �ð��� �����ϱ� ������ �� * 1000)
				serverSocket.setSoTimeout(5*1000);
				Socket socket =serverSocket.accept();
				System.out.println(getTime() + socket.getInetAddress()+ "�κ��� �����û�� ���Խ��ϴ�");
				
				// Ŭ���̾�Ʈ�� port
				System.out.println("getPort() =" + socket.getPort());
				// ������ port
				System.out.println("getLoclaPort() =" + socket.getLocalPort());
				
				OutputStream out = socket.getOutputStream();
				DataOutputStream dos = new DataOutputStream(out);
				
				dos.writeUTF("[Notice] Test Message from Server.");
				System.out.println(getTime() + "Ŭ���̾�Ʈ�� �����͸� �����մϴ�");
				
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
