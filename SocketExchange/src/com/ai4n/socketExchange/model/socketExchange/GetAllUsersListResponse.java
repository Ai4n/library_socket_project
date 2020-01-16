package com.ai4n.socketExchange.model.socketExchange;

import com.ai4n.entities.user.User;
import com.ai4n.socketExchange.model.ServerMessage;
import com.ai4n.socketExchange.model.SocketExchange;

import java.util.ArrayList;

public class GetAllUsersListResponse extends SocketExchange {

	private ArrayList<User> usersList;

	public GetAllUsersListResponse() {
		super(ServerMessage.GET_ALL_USERS);
	}

	public GetAllUsersListResponse(ArrayList<User> usersList) {
		super(ServerMessage.GET_ALL_USERS);
		this.usersList = usersList;
	}

	public ArrayList<User> getAllUsersList() {
		return usersList;
	}
}
