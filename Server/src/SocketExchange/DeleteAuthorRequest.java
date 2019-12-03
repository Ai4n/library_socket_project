package SocketExchange;

import com.google.gson.Gson;

import Main.ServerMessage;

public class DeleteAuthorRequest extends SocketExchange {

	private int authorId;
	public DeleteAuthorRequest(int authorId) {
		super(ServerMessage.DELETE_AUTHOR);
		this.authorId = authorId;
	}
	
	public int getAuthorId() {
		return authorId;
	}
	
	public String json() {
		return new Gson().toJson(this);
	}

}
