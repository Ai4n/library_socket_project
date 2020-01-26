package nioTest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ServerChannel {

    public static void start() throws IOException {
        Selector selector = Selector.open(); // 1

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open(); // 2
        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 5454);
        serverSocketChannel.bind(hostAddress); // 3
        serverSocketChannel.configureBlocking(false);

        SelectionKey selectKy = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, null); // 4

        while(true) {
            System.out.println("Waiting for select...");
            int noOfKeys = selector.select();

            System.out.println("Number of selected keys: " + noOfKeys);

            Set selectedKeys = selector.selectedKeys(); //[]
            Iterator iter = selectedKeys.iterator();

            while (iter.hasNext()) {

                SelectionKey ky = (SelectionKey) iter.next(); //1) return current value 2) moves iterator

                if (ky.isAcceptable()) {

                    // Accept the new client connection
                    SocketChannel client = serverSocketChannel.accept();
                    client.configureBlocking(false);

                    // Add the new connection to the selector
                    client.register(selector, SelectionKey.OP_READ);

//                    System.out.println("Accepted new connection from client: " + client);
                }
                else if (ky.isReadable()) {
                    // Read the data from client
                    SocketChannel socketChannel = (SocketChannel) ky.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(256); //[....................]
                    System.out.println("buffer before reading " + buffer);
                    socketChannel.read(buffer);
                    System.out.println("buffer after reading " + buffer);

                    String output = new String(buffer.array()).trim();

                    System.out.println("Message read from client: " + output);

                    if (output.equals("Bye.")) {

                        socketChannel.close();
                        System.out.println("Client messages are complete; close.");
                    }
                }

                iter.remove();

            }
        }
    }
}
