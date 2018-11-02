package com.scsa.chat.step1;

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
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new PrintWriter(s.getOutputStream(), true);
			
			String str = null;
			while ((str = in.readLine()) != null && str.trim().length() != 0) {
				System.out.println("client �޼���: " + str);
				out.println("server���� ����: "+str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (s != null)
					s.close();
				if (ss != null)
					ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
