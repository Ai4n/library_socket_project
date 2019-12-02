package SocketExchange;

import com.google.gson.Gson;

import Main.ServerMessage;

public class SearchBooksRequest extends SocketExchange {

	String textForSearch;

	public SearchBooksRequest(String textForSearch) {
		super(ServerMessage.SEARCH_BOOK);
		this.textForSearch = textForSearch;
	}

	public String getTextForSearch() {
		return textForSearch;
	}

	public String json() {
		return new Gson().toJson(this);
	}
}
