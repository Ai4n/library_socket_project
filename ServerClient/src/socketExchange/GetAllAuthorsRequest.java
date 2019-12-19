package socketExchange;
import main.ServerMessage;

public class GetAllAuthorsRequest extends SocketExchange {

	public GetAllAuthorsRequest() {
		super(ServerMessage.GET_ALL_AUTHORS);
	}
}
