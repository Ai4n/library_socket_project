package socket.model.socketExchange;

import socket.model.serverMessage.ServerMessage;

public class SearchBookRequest extends SocketExchange {

	String textForSearch;

	public SearchBookRequest(String textForSearch) {
		super(ServerMessage.SEARCH_BOOK);
		this.textForSearch = textForSearch;
	}

	public String getTextForSearch() {
		return textForSearch;
	}
}
