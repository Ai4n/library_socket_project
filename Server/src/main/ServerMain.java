package main;

import com.ai4n.socketExchange.controller.SocketController;
import com.ai4n.socketExchange.model.SocketExchange;
import main.controller.ServerController;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class ServerMain {


	public static void main(String args[]) {
		try {
			HashMap<SocketAddress, ServerController> clientPool = new HashMap<>();

			Selector selector = Selector.open(); // 1

			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open(); // 2
			InetSocketAddress hostAddress = new InetSocketAddress("localhost", 5454);
			serverSocketChannel.bind(hostAddress); // 3
			serverSocketChannel.configureBlocking(false);

			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, null); // 4

			while (true) {
				System.out.println("Waiting for select...");
				int noOfKeys = selector.select();

				System.out.println("Number of selected keys: " + noOfKeys);

				Set selectedKeys = selector.selectedKeys(); //[]
				Iterator iter = selectedKeys.iterator();

				while (iter.hasNext()) {

					SelectionKey ky = (SelectionKey) iter.next(); //1) return current value 2) moves iterator

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
						SocketChannel socketChannel = (SocketChannel) ky.channel();
						ServerController serverController = clientPool.get(socketChannel.getRemoteAddress());
						ByteBuffer buffer = ByteBuffer.allocate(1024);
						socketChannel.read(buffer);
						String output = new String(buffer.array()).trim();
						System.out.println("Message read from client: " + output);
						SocketController socketController = new SocketController(socketChannel);
						SocketExchange socketExchange = socketController.getRequestData(output);
						serverController.processMessage(socketExchange);
					}
					iter.remove();
				}
			}
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}

}

