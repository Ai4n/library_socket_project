package socketExchange;

import main.ServerMessage;

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
