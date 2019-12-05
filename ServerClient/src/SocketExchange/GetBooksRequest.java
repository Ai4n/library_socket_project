package SocketExchange;

import com.google.gson.Gson;

import Main.ServerMessage;

public class GetBooksRequest extends SocketExchange {

	String textForSearch;

	public GetBooksRequest(String textForSearch) {
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
