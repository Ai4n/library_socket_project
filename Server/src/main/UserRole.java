package main;

public enum UserRole {
	USER("USER"),
	ADMIN("ADMIN");
	
	private String role;
	
	public static UserRole create(String role) {
		if(role.equals("ADMIN")) {
			return ADMIN;
		} 
		return USER;
	}
	
	UserRole (String role) {
		this.role = role;
	}
	
	public String toString() {
		return role;
	}
}
