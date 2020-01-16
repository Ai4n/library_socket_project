package com.ai4n.socketExchange.controller;

import com.ai4n.socketExchange.model.SocketExchange;
import com.google.gson.Gson;

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

	public SocketExchange convertMessage() {
		try {
			Gson gson = new Gson();
			return gson.fromJson(dataIn.readUTF(), SocketExchange.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public <T extends SocketExchange> T convertMessage(String jsonString, T object) {
		try {
			Class c = object.getClass();
			return (T)new Gson().fromJson(jsonString, c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public SocketExchange readMessage() {
		try {
			String json = dataIn.readUTF();
			SocketExchange request = new Gson().fromJson(json, SocketExchange.class);
			request.json = json;
			return request;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
