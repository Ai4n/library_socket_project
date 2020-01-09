package main;

import controllers.ClientController;
import java.net.Socket;

public class ClientMain {

    public static void main(String args[]) {

        try {
            Socket socket = new Socket("127.0.0.1", 5555);
            ClientController controller = new ClientController(socket);
            controller.startMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}