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
			System.out.println("접속 대기 중");
			s = ss.accept(); // holding
			System.out.println(s.getInetAddress() + "이(가) 접속했습니다.");
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new PrintWriter(s.getOutputStream(), true);
			
			String str = null;
			while ((str = in.readLine()) != null && str.trim().length() != 0) {
				System.out.println("client 메세지: " + str);
				out.println("server에서 보냄: "+str);
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
