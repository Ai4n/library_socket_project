package com.ai4n.socketExchange.model.socketExchange;

import com.ai4n.socketExchange.model.ServerMessage;
import com.ai4n.socketExchange.model.SocketExchange;

public class SearchBookRequest extends SocketExchange {

	String textForSearch;

	public SearchBookRequest() {
		super(ServerMessage.SEARCH_BOOK);
	}

	public SearchBookRequest(String textForSearch) {
		super(ServerMessage.SEARCH_BOOK);
		this.textForSearch = textForSearch;
	}

	public String getTextForSearch() {
		return textForSearch;
	}
}
