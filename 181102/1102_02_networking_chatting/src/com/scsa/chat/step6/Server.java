package com.scsa.chat.step6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	ServerSocket ss;
	ArrayList<ServerThread> clients;
	
	public static void main(String[] args) {
		Server server = new Server();
		server.go();
	}

	public void go() {
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			clients = new ArrayList<ServerThread>();
			ss = new ServerSocket(7777);
			System.out.println("접속 대기 중");
			Socket s = ss.accept(); // holding
			System.out.println(s.getInetAddress() + "이(가) 접속했습니다.");

			////////////////////////////////////////////////////
			new ServerThread(this, s).start();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ss != null)
					ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void broadcast(String message) {
		for (ServerThread serverThread : clients) {
			serverThread.sendMessage(message);
		}
	}
	public void addClient(ServerThread c) {
		clients.add(c);
	}
	public void removeClient(ServerThread c) {
		clients.remove(c);
	}
}

class ServerThread extends Thread {	
	private Socket s;
	BufferedReader in = null;
	PrintWriter out = null;
	Server server = null;
	
	public ServerThread(Server server, Socket s) throws IOException {
		this.s = s;
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		out = new PrintWriter(s.getOutputStream(), true);
		
		server.addClient(this);
	}

	@Override
	public void run() {
		String str = null;
		try {
			while((str=in.readLine())!=null && str.trim().length()!=0   ) {
				System.out.println("client 메세지: "+str);
				server.broadcast(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (s != null)
			try {
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				server.removeClient(this);
			}
	}
	public void sendMessage(String message) {
		
		out.println("server에서 보낸 메세지: " + message);
	}
	
}
