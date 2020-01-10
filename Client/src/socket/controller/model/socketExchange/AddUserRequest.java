package socket.controller.model.socketExchange;
import entities.user.User;
import socket.controller.model.ServerMessage;
import socket.controller.model.SocketExchange;

public class AddUserRequest extends SocketExchange {

	private User user;

	public AddUserRequest(User user) {
		super(ServerMessage.ADD_USER);
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}
