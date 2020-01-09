package socket.model.socketExchange;
import entities.user.User;
import socket.model.serverMessage.ServerMessage;

public class UserCheckResponse extends SocketExchange {
	
	private User user;
	
	public UserCheckResponse(boolean isCredentialsCorrect, User user) {
		super(isCredentialsCorrect ? ServerMessage.USER_EXIST : ServerMessage.USER_NOT_EXIST);
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}
