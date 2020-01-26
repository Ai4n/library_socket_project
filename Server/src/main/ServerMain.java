package main;

import main.controller.ServerController;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

	public static void main(String args[]) {
		ServerSocket serverSocket;
		Socket socket;

			try {
				serverSocket = new ServerSocket(5555);
				while (true) {
					socket = serverSocket.accept();
					ServerController serverController = new ServerController(socket);
					serverController.start();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

	}
}
