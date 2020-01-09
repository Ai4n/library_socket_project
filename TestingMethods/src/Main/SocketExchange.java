package Main;

import com.google.gson.Gson;

public class SocketExchange {
	
	int id;
	String title;
	
	public SocketExchange(int id, String title) {
		this.id = id;
		this.title = title;
	}
	
	public <T> T create(String jsonString) {
		return (T)new Gson().fromJson(jsonString, this.getClass());
	}
	
}
