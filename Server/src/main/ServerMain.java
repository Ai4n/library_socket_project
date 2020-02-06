package main;

import main.controller.ServerController;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class ServerMain {
    static HashMap<SocketAddress, ServerController> clientPool;
    static Selector selector;
    static ServerSocketChannel serverSocketChannel;
	static InetSocketAddress hostAddress;

    public static void main(String args[]) {
        try {
            clientPool = new HashMap<>();
            selector = Selector.open(); // 1
            serverSocketChannel = ServerSocketChannel.open(); // 2
			hostAddress = new InetSocketAddress("localhost", 5454);
            serverSocketChannel.bind(hostAddress); // 3
            serverSocketChannel.configureBlocking(false);

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, null); // 4

            while (true) {
                System.out.println("Waiting for select...");
                int noOfKeys = selector.select(); // 1<<2<<3<<4

                System.out.println("Number of selected keys: " + noOfKeys);

                Set<SelectionKey> selectedKeys = selector.selectedKeys(); //[]
                Iterator<SelectionKey> iter = selectedKeys.iterator();

                while (iter.hasNext()) {

                    SelectionKey ky = iter.next(); //1) return current value 2) moves iterator
                    iter.remove();

                    if (ky.isAcceptable()) {
                        // Accept the new client connection
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        // Add the new connection to the selector
                        socketChannel.register(selector, SelectionKey.OP_READ);

                        ServerController serverController = new ServerController(socketChannel);
                        clientPool.put(socketChannel.getRemoteAddress(), serverController);

                        System.out.println("Accepted new connection from client: " + socketChannel);
                    } else if (ky.isReadable()) {
                        ServerController serverController = clientPool.get(((SocketChannel) ky.channel()).getRemoteAddress());
                        serverController.processRequest();
                    }

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

