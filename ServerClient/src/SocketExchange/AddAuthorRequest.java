package SocketExchange;

import com.google.gson.Gson;

import Main.*;

public class AddAuthorRequest extends SocketExchange {

	private Author author;
	
	public AddAuthorRequest(Author author) {
		super(ServerMessage.ADD_AUTHOR);
		this.author = author;
	}

	public Author getAuthor() {
		return author;
	}
	
	public String json() {
		return new Gson().toJson(this);
	}
}