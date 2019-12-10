package socketExchange;

import main.ServerMessage;

public class DeleteUserRequest extends SocketExchange {

	private int idUser;

	public DeleteUserRequest(int idUser) {
		super(ServerMessage.DELETE_USER);
		this.idUser = idUser;
	}

	public int getIdUser() {
		return idUser;
	}
}
