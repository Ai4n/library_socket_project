package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {

	public static void main(String args[]) {
		ServerSocket serverSocket;
		Socket socket;
		try {
			serverSocket = new ServerSocket(5555);
			socket = serverSocket.accept();
			ServerController serverController = new ServerController(socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
