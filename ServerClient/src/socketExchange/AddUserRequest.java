package socketExchange;

import com.google.gson.Gson;

import main.ServerMessage;
import main.User;
import main.UserRole;

public class AddUserRequest extends SocketExchange {

	private User user;

	public AddUserRequest(User user) {
		super(ServerMessage.ADD_USER);
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}
