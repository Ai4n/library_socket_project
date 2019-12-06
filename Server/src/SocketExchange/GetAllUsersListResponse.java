package SocketExchange;

import java.util.ArrayList;

import com.google.gson.Gson;

import Main.ServerMessage;
import Main.User;

public class GetAllUsersListResponse extends SocketExchange {

	private ArrayList<User> usersList;

	public GetAllUsersListResponse(ArrayList<User> usersList) {
		super(ServerMessage.GET_ALL_USERS);
		this.usersList = usersList;
	}

	public ArrayList<User> getAllUsersList() {
		return usersList;
	}

	public String json() {
		return new Gson().toJson(this);
	}
}
