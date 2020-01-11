package socket.model.socketExchange;
import entities.user.User;
import socket.model.ServerMessage;
import socket.model.SocketExchange;

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
