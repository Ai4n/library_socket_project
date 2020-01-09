package socket.controller.controller.socketExchange;
import model.ServerMessage;

public class GetAllUsersListRequest extends SocketExchange {
	
	public GetAllUsersListRequest() {
		super(ServerMessage.GET_ALL_USERS);
	}
}
