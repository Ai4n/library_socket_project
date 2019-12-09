package socketExchange;

import com.google.gson.Gson;

import main.ServerMessage;
import main.User;

public class UserCheckResponse extends SocketExchange {
	
	private User user;
	
	public UserCheckResponse(boolean isCredentialsCorrect, User user) {
		super(isCredentialsCorrect ? ServerMessage.USER_EXIST : ServerMessage.USER_NOT_EXIST);
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public String json() {
		return new Gson().toJson(this);
	}
}
