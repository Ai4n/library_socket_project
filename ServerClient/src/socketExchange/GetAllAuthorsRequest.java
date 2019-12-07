package socketExchange;

import com.google.gson.Gson;

import main.ServerMessage;

public class GetAllAuthorsRequest extends SocketExchange {

	public GetAllAuthorsRequest() {
		super(ServerMessage.GET_ALL_AUTHORS);
	}

	public String json() {
		return new Gson().toJson(this);
	}
	
}