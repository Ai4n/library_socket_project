package Main;

import java.util.ArrayList;
import com.google.gson.*;

public class MainTestClass {
	
	public static void main(String[] args) {
//		SocketExchange testObject = new SocketExchange(1, "test");
		Gson gson = new Gson();
		Request request = new Request("test", 1, 4);
		String json = gson.toJson(request);
		
		Request parsedJson = SocketExchange
		
	}


}
