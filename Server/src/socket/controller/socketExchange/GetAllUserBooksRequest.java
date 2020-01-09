package socket.controller.controller.socketExchange;
import model.ServerMessage;

public class GetAllUserBooksRequest extends SocketExchange {

	private int userId;
	
	public GetAllUserBooksRequest(int userId) {
		super(ServerMessage.SHOW_BOOKS);
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}
}