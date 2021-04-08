package chapter2;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TcpMultichatServer {
public static Map<String, DataOutputStream> clients;
	
	public TcpMultichatServer() {
		// �����忡 ������ �ؽø�
		// �Ѿ����忡 �����ϴ� -> �� �����尡 �� ���� ����ϰ� ������
		// �ٸ� ��������� �̸��� ����Ҽ� ���Բ� ����°�
		clients = new ConcurrentHashMap<>();
	}
	
	public void start() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		
		try {
			serverSocket = new ServerSocket(7777);
			while (true) {
				socket = serverSocket.accept();
				System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] ���� �����Ͽ����ϴ�" );
				
				ServerReceiver thread  = new ServerReceiver(socket);
				thread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("������ ���۵Ǿ����ϴ�");
			
	}
	public static void sendToAll(String msg) {
		Iterator<String> it = clients.keySet().iterator();
		while (it.hasNext()) {
			try {
				String key = it.next();
				DataOutputStream out = (DataOutputStream) clients.get(key);
				out.writeUTF(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			
	}
	public static void main(String[] args) {
		new TcpMultichatServer().start();
	}

}
