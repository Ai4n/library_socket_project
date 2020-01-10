package socket.controller.socketExchange;

import socket.controller.model.ServerMessage;

public class DeleteAuthorRequest extends SocketExchange {

	private int authorId;
	public DeleteAuthorRequest(int authorId) {
		super(ServerMessage.DELETE_AUTHOR);
		this.authorId = authorId;
	}
	
	public int getAuthorId() {
		return authorId;
	}
}
