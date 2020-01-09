package socket.model.socketExchange;
import entities.book.Author;
import socket.model.serverMessage.ServerMessage;

public class AddAuthorRequest extends SocketExchange {

	private Author author;
	
	public AddAuthorRequest(Author author) {
		super(ServerMessage.ADD_AUTHOR);
		this.author = author;
	}

	public Author getAuthor() {
		return author;
	}
}
