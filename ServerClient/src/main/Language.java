package main;

public enum Language {

	ENG("ENG"),
	RUS("RUS"),
	FRENCH("FRENCH"),
	DEF("DEF");
	
	private String message;

	Language(String message){
		this.message = message;
	}
	
	public static Language create(String message) {
        for (Language languageMessage : Language.values()) {
            if (languageMessage.message.equals(message)) {
                return languageMessage;
            }
        }
        return null;
    }
	
	public String toString() {
		return message;
	}
}
