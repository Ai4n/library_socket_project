package SocketExchange;

import com.google.gson.Gson;

import Main.ServerMessage;

public class DeleteBookRequest extends SocketExchange {

	private int bookId;

	public DeleteBookRequest(int bookId) {
		super(ServerMessage.DELETE_BOOK);
		this.bookId = bookId;
	}

	public int getBookId() {
		return bookId;
	}

	public String json() {
		return new Gson().toJson(this);
	}
}
