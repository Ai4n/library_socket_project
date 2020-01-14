package com.ai4n.socketExchange.model.socketExchange;

import com.ai4n.entities.book.Author;
import com.ai4n.socketExchange.model.ServerMessage;
import com.ai4n.socketExchange.model.SocketExchange;

import java.util.ArrayList;

public class GetAllAuthorsResponse extends SocketExchange {
	
	private ArrayList<Author> authorsList;
	
	public GetAllAuthorsResponse(ArrayList<Author> authorsList) {
		super(ServerMessage.GET_ALL_AUTHORS);
		this.authorsList = authorsList;
	}

	public ArrayList<Author> getAuthorsList() {
		return authorsList;
	}
}
