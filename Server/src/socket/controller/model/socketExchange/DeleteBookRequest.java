package socket.controller.model.socketExchange;

import socket.controller.model.ServerMessage;
import socket.controller.model.SocketExchange;

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
