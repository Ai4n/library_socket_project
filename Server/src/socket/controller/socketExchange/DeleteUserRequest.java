package socket.controller.controller.socketExchange;
import model.ServerMessage;

public class DeleteUserRequest extends SocketExchange {

	private int userId;

	public DeleteUserRequest(int userId) {
		super(ServerMessage.DELETE_USER);
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}
}