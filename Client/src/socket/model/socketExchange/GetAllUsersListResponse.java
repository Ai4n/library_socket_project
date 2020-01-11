package socket.model.socketExchange;
import java.util.ArrayList;

import entities.user.User;
import socket.model.ServerMessage;
import socket.model.SocketExchange;

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
