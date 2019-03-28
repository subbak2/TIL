package com.scsa.chat.step6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	Socket s;

	public static void main(String[] args) {
		Client c = new Client();
		c.go();
	}

	public void go() {
		PrintWriter out = null;
		BufferedReader in = null;
		BufferedReader sin = null;
		try {
			System.out.println("Client......");
			s = new Socket("127.0.0.1", 7777);
			new ClientThread(s).start();
			
			out = new PrintWriter(s.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(System.in));
			sin = new BufferedReader(new InputStreamReader(s.getInputStream()));

			String str = null;
			while ((str = in.readLine()) != null && str.trim().length() != 0) {
				out.println(str);
				System.out.println(sin.readLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (s != null)
				try {
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		System.out.println("연결이 종료되었습니다...");
	}
}

class ClientThread extends Thread {
	BufferedReader sin = null;
	Socket s;

	public ClientThread(Socket s) throws IOException {
		this.s = s;
		sin = new BufferedReader(new InputStreamReader(s.getInputStream()));
	}

	@Override
	public void run() {
		String str = null;
		try {
			while ((str = sin.readLine()) != null) {
				System.out.println(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (s != null)
				try {
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
