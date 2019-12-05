package SocketExchange;

import com.google.gson.Gson;
import Main.ServerMessage;
import Main.User;

public class IsLoginExistResponse extends SocketExchange {

	public IsLoginExistResponse(Boolean isLoginExist) {
		super(isLoginExist ? ServerMessage.USER_EXIST : ServerMessage.USER_NOT_EXIST);
	}

	public String json() {
		return new Gson().toJson(this);
	}
}
