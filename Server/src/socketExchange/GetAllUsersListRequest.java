package socketExchange;

import com.google.gson.Gson;

import main.ServerMessage;

public class GetAllUsersListRequest extends SocketExchange {
	
	public GetAllUsersListRequest() {
		super(ServerMessage.GET_ALL_USERS);
	}

	public String json() {
		return new Gson().toJson(this);
	}
}
