package com.ai4n.socketExchange.controller;

import com.ai4n.socketExchange.model.SocketExchange;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketController {
	private ObjectOutputStream dataOut;
	private ObjectInputStream dataIn;
	private Socket socket;

	public SocketController(Socket socket) throws IOException {
		dataOut = new ObjectOutputStream(socket.getOutputStream());
		dataIn = new ObjectInputStream(socket.getInputStream());
		this.socket = socket;
	}

	public void closeSession()  {
		try {
			socket.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void write(SocketExchange data) {
		try {
			dataOut.writeUTF(data.json());
			dataOut.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public String readUtf() {
		try {
			return dataIn.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
