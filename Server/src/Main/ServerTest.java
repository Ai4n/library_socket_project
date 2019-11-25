package Main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {
	private static ServerController serverController;

	public static void main(String args[]) {
		
		ServerSocket serverSocket;
		Socket socket;
		try {
			serverSocket = new ServerSocket(5555);
			socket = serverSocket.accept();
			setServerController(new ServerController(socket));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static ServerController getServerController() {
		return serverController;
	}

	public static void setServerController(ServerController serverController) {
		ServerTest.serverController = serverController;
	}

}
