package com.ai4n.socketExchange.controller;

import com.ai4n.socketExchange.model.SocketExchange;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.sql.SQLOutput;

public class SocketController {
	private SocketChannel socketChannel;

	public SocketController(SocketChannel socketChannel) throws IOException {
		this.socketChannel = socketChannel;
	}

	public void closeSession() {
		try {
			socketChannel.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void write(SocketExchange data) {
		try {

			byte[] messageBytes = data.json().getBytes();
			ByteBuffer buffer = ByteBuffer.wrap(messageBytes);
			socketChannel.write(buffer);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
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

	public SocketExchange getRequestData(String jsonString) {
		try {
			SocketExchange request = new Gson().fromJson(jsonString, SocketExchange.class);
			request.json = jsonString;
			return request;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public SocketExchange readMessage() {
		try {
			ByteBuffer buffer = ByteBuffer.allocate(256);
			socketChannel.read(buffer);
			String json = new String(buffer.array()).trim();
			System.out.println(json);
			SocketExchange request = new Gson().fromJson(json, SocketExchange.class);
			request.json = json;
			return request;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
