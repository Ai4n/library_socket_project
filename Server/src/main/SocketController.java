package main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class SocketController {
	private ObjectOutputStream dataOut;
	private ObjectInputStream dataIn;

	public SocketController(Socket socket) throws IOException {
		dataOut = new ObjectOutputStream(socket.getOutputStream());
		dataIn = new ObjectInputStream(socket.getInputStream());
	}

	public void write(String json) {
		try {
			dataOut.writeUTF(json);
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
