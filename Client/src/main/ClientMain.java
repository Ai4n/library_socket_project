package main;

import controllers.ClientController;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;

public class ClientMain {

    public static void main(String args[]) {
        try {
            InetSocketAddress address = new InetSocketAddress("localhost", 5454);
            SocketChannel socketChannel = SocketChannel.open(address);
            ClientController controller = new ClientController(socketChannel);
            controller.startMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}