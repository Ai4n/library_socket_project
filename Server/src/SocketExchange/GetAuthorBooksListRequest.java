package SocketExchange;

import com.google.gson.Gson;

import Main.ServerMessage;

public class GetAuthorBooksListRequest extends SocketExchange {

	private int authorId;
	public GetAuthorBooksListRequest(int authorId) {
		super(ServerMessage.SHOW_AUTHORS_BOOKS);
		this.authorId = authorId;
	}

	public int getAuthorId() {
		return authorId;
	}
	
	public String json() {
		return new Gson().toJson(this);
	}
}
