package socketExchange;

import java.util.ArrayList;

import main.ServerMessage;
import main.User;

public class GetAllUsersListResponse extends SocketExchange {

	private ArrayList<User> usersList;

	public GetAllUsersListResponse(ArrayList<User> usersList) {
		super(ServerMessage.GET_ALL_USERS);
		this.usersList = usersList;
	}

	public ArrayList<User> getAllUsersList() {
		return usersList;
	}
}
