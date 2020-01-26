package nioTest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

public class ClientChannel {
    public static void start() throws IOException, InterruptedException {
        InetSocketAddress address = new InetSocketAddress("localhost", 5454);
        SocketChannel socketChannel = SocketChannel.open(address);

        ArrayList<String> messages = new ArrayList<>();
        messages.add("Hello");
        messages.add("Message");
        messages.add("Bye");

        for(String message: messages) {
            byte[] messageBytes = message.getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(messageBytes);
            socketChannel.write(buffer);
            //String - > byte - WRITE
            //Byte -> String - READ
            System.out.println("sent " + message + " " + messageBytes.length + " bytes");
            buffer.clear();
            Thread.sleep(1000);
        }

    }
}
