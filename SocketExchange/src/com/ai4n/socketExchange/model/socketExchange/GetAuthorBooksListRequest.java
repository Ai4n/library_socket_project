package com.ai4n.socketExchange.model.socketExchange;

import com.ai4n.socketExchange.model.ServerMessage;
import com.ai4n.socketExchange.model.SocketExchange;

public class GetAuthorBooksListRequest extends SocketExchange {

	private int authorId;
	public GetAuthorBooksListRequest(int authorId) {
		super(ServerMessage.SHOW_AUTHORS_BOOKS);
		this.authorId = authorId;
	}

	public int getAuthorId() {
		return authorId;
	}
}
