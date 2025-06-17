package com.sinse.networkapp.multicast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

// 메시지를 보낼 때만 청취를 하게 되면, 실시간으로 서버에서 진행되고 있는 메시지들을 모두 받을 수 없음...
// 유저가 특별한 액션을 취하지 않아도 실시간으로 메시지를 받기 위해 쓰레드로 정의하고, 특히 listen()을 무한 루프로 두자
public class ClientChatThread extends Thread {
	Client client; //area 등을 보유한 GUI 클래스
	Socket socket; // 전화기
	BufferedReader buffr; // 듣는 실
	BufferedWriter buffw; // 말하는 실
	
	public ClientChatThread(Client client, Socket socket) {
		this.client = client;
		this.socket = socket;
		
		try {
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 서버에 메시지 보내기(사용자가 보내기 원할 때) : 실시간 지속적 호출 아님...
	public void send(String msg) {
		try {
			buffw.write(msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 서버의 메시지를 실시간 청취한다
	public void listen() {
		String msg = null;
		try {
			msg = buffr.readLine();
			client.area.append(msg+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(true) { // run은 절대 죽으면 안되기 때문에 무한루프에 빠뜨린다..
			listen();
		}
	}
}
