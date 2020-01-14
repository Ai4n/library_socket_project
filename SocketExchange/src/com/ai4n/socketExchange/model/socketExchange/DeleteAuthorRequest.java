package com.ai4n.socketExchange.model.socketExchange;

import com.ai4n.socketExchange.model.ServerMessage;
import com.ai4n.socketExchange.model.SocketExchange;

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
