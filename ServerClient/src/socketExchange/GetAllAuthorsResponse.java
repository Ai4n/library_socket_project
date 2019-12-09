package socketExchange;

import java.util.ArrayList;

import com.google.gson.Gson;

import main.Author;
import main.ServerMessage;

public class GetAllAuthorsResponse extends SocketExchange {

	private ArrayList<Author> authorsList;

	public GetAllAuthorsResponse(ArrayList<Author> authorsList) {
		super(ServerMessage.GET_ALL_AUTHORS);
		this.authorsList = authorsList;
	}

	public ArrayList<Author> getAuthorsList() {
		return authorsList;
	}

	public String json() {
		return new Gson().toJson(this);
	}

}
