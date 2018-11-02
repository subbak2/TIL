package com.scsa.chat.step4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	ServerSocket ss;
	Socket s;

	public static void main(String[] args) {
		Server server = new Server();
		server.go();
	}

	public void go() {
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			ss = new ServerSocket(7777);
			System.out.println("���� ��� ��");
			s = ss.accept(); // holding
			System.out.println(s.getInetAddress() + "��(��) �����߽��ϴ�.");

			////////////////////////////////////////////////////
			new ServerThread(s).start();
			
			
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
}

class ServerThread extends Thread {
	private Socket s;
	BufferedReader in;
	PrintWriter out;

	public ServerThread(Socket s) throws IOException {
		this.s = s;
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		out = new PrintWriter(s.getOutputStream(), true);
	}

	@Override
	public void run() {
		String str = null;
		try {
			while((str=in.readLine())!=null && str.trim().length()!=0   ) {
				System.out.println("client �޼���: "+str);
				out.println("server���� ���� �޼���: " + str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (s != null)
			try {
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
