package socketExchange;

import com.google.gson.Gson;

import main.ServerMessage;

public class IsLoginExistRequest extends SocketExchange {

	private String newLogin;

	public IsLoginExistRequest(String newLogin) {
		super(ServerMessage.LOGIN_CHECK);
		this.newLogin = newLogin;
	}

	public String getNewLogin() {
		return newLogin;
	}

	public String json() {
		return new Gson().toJson(this);
	}

}
