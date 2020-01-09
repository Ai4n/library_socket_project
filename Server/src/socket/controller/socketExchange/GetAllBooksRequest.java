package socket.controller.controller.socketExchange;
import model.ServerMessage;

public class GetAllBooksRequest extends SocketExchange {

	public GetAllBooksRequest() {
		super(ServerMessage.GET_ALL_BOOKS);
	}
}
