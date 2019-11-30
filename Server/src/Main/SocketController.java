package Main;

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

	public void writeInt(ServerMessage serverMessage, int... values) {
		write(serverMessage, convertArrayIntegerToArrayString(values));
	}

	private String [] convertArrayIntegerToArrayString(int[] arrayInt) {
		ArrayList <String> arrayStr = new ArrayList<>();
		for(int i = 0; i < arrayInt.length; i++) {
			arrayStr.add(String.valueOf(arrayInt[i]));
			} 
		String[] array = arrayStr.toArray(new String[arrayStr.size()]);
		return array;
	} 

	public void write(ServerMessage serverMessage, String... values) {
		try {
			dataOut.writeUTF(serverMessage.getMessage());
			for (String value : values) {
				dataOut.writeUTF(value);
			}
			dataOut.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public <T> void writeObject(ServerMessage serverMessage, T object) {
		try {
			dataOut.writeUTF(serverMessage.getMessage());
			dataOut.writeObject(object);
			dataOut.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void writeMessage(ServerMessage serverMessage) {
		try {
			dataOut.writeUTF(serverMessage.getMessage());
			dataOut.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void writeObject(Object object) {
		try {
			dataOut.writeObject(object);
			dataOut.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
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
	
	public int readInt()  {
		try {
			return dataIn.readInt();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;	
	}
	
	public Object readObject() {
		try {
			return dataIn.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}

	@SuppressWarnings("unchecked")
	public <T> T read() {
		try {
			return (T) dataIn.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
