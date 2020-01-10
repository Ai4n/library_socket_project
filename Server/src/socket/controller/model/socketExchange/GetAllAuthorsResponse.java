package socket.controller.model.socketExchange;

import java.util.ArrayList;
import entities.book.Author;
import socket.controller.model.ServerMessage;
import socket.controller.model.SocketExchange;

public class GetAllAuthorsResponse extends SocketExchange {
	
	private ArrayList<Author> authorsList;
	
	public GetAllAuthorsResponse(ArrayList<Author> authorsList) {
		super(ServerMessage.GET_ALL_AUTHORS);
		this.authorsList = authorsList;
	}

	public ArrayList<Author> getAuthorsList() {
		return authorsList;
	}
}
