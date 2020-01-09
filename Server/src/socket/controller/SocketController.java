package main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import socketExchange.SocketExchange;

public class SocketController {
	private ObjectOutputStream dataOut;
	private ObjectInputStream dataIn;
	
	public SocketController(Socket socket) throws IOException {
		dataOut = new ObjectOutputStream(socket.getOutputStream());
		dataIn = new ObjectInputStream(socket.getInputStream());
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
