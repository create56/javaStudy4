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
		// 쓰레드에 안전한 해시맵
		// 한쓰레드에 안전하다 -> 한 쓰레드가 이 맵을 사용하고 있을떄
		// 다른 쓰레드들은 이맵을 사용할수 없게끔 만드는것
		clients = new ConcurrentHashMap<>();
	}
	
	public void start() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		
		try {
			serverSocket = new ServerSocket(7777);
			while (true) {
				socket = serverSocket.accept();
				System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] 에서 접속하였습니다" );
				
				ServerReceiver thread  = new ServerReceiver(socket);
				thread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("서버가 시작되었습니다");
			
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
