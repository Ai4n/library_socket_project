package socketExchange;

import com.google.gson.Gson;

import main.ServerMessage;

public class UserCheckRequest extends SocketExchange{

	private String login;
	private String password;
	
	public UserCheckRequest(String login, String password) {
		super(ServerMessage.USER_CHECK);
		this.login = login;
		this.password = password;
	}
	
	public String json() {
		return new Gson().toJson(this);
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

}