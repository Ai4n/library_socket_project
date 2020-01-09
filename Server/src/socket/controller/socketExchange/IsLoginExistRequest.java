package socket.controller.controller.socketExchange;
import model.ServerMessage;

public class IsLoginExistRequest extends SocketExchange {

	private String newLogin;

	public IsLoginExistRequest(String newLogin) {
		super(ServerMessage.LOGIN_CHECK);
		this.newLogin = newLogin;
	}

	public String getNewLogin() {
		return newLogin;
	}
}