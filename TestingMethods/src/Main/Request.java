package Main;

public class Request extends SocketExchange {
	String field;
	
	public Request(String str, int integer, int id) {
		super(integer, str);
		this.id = id;
	}
	
}
