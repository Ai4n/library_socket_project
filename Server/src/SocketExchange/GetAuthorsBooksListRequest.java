package SocketExchange;

import com.google.gson.Gson;

import Main.ServerMessage;

public class GetAuthorsBooksListRequest extends SocketExchange {

	private int authorId;
	public GetAuthorsBooksListRequest(int authorId) {
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
