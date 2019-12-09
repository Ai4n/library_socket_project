package socketExchange;

import com.google.gson.Gson;

import main.ServerMessage;

public class SearchBookRequest extends SocketExchange {

	String textForSearch;

	public SearchBookRequest(String textForSearch) {
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
