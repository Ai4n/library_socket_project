package socket.controller.socketExchange;

import socket.controller.model.ServerMessage;

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
