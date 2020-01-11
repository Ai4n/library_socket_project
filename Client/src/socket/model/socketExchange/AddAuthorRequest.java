package socket.model.socketExchange;
import entities.book.Author;
import socket.model.ServerMessage;
import socket.model.SocketExchange;

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
