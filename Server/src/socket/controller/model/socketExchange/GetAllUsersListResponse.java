package socket.controller.model.socketExchange;

import java.util.ArrayList;
import socket.controller.model.ServerMessage;
import entities.user.User;
import socket.controller.model.SocketExchange;

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
