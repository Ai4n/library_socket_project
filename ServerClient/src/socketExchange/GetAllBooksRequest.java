package socketExchange;

import com.google.gson.Gson;

import main.ServerMessage;

public class GetAllBooksRequest extends SocketExchange {

	public GetAllBooksRequest() {
		super(ServerMessage.GET_ALL_BOOKS);
	}

	public String json() {
		return new Gson().toJson(this);
	}
}
