package main;

public enum UserRole {
	USER("user"), ADMIN("admin");

	private String message;

	UserRole(String message) {
		this.message = message;
	}

	public static UserRole create(String message) {
		for (UserRole userRole : UserRole.values()) {
			if (userRole.message.equals(message)) {
				return userRole;
			}
		}
		return null;
	}

	public String getMessage() {
		return message;
	}
}