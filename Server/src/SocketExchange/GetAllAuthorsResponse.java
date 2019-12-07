package SocketExchange;

import java.util.ArrayList;

import com.google.gson.Gson;

import Main.Author;
import Main.ServerMessage;

public class GetAllAuthorsResponse extends SocketExchange{
	
	private ArrayList<Author> authorsList;
	public GetAllAuthorsResponse(ArrayList<Author> authorsList) {
		super(ServerMessage.GET_ALL_AUTHORS);
		this.authorsList = authorsList;
	}

	public String json() {
		return new Gson().toJson(this);
	}
	
	
}
