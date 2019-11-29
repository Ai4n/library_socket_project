package SocketRequests;

import Main.*;

public class AddAuthorRequest extends SocketRequest {

	private Author author;
	
	public AddAuthorRequest(Author author) {
		super(ServerMessage.ADD_AUTHOR);
		this.author = author;
	}

	public Author getAuthor() {
		return author;
	}
	
}
