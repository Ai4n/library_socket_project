package socket.model.socketExchange;

import socket.model.serverMessage.ServerMessage;

public class DeleteBookRequest extends SocketExchange {

	private int bookId;

	public DeleteBookRequest(int bookId) {
		super(ServerMessage.DELETE_BOOK);
		this.bookId = bookId;
	}

	public int getBookId() {
		return bookId;
	}
}