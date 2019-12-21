package main;

import java.io.Serializable;

public class Author implements Serializable {
	private int authorId;
	private String name;
	private String surname;
	private Language language;
	
	private static final long serialVersionUID = 7L;
	
	public Author (int authorId, String name, String surname, Language language) {
		this.authorId = authorId;
		this.name = name;
		this.surname = surname;
		this.language = language;
	}
	
//	public Author (String name, String surname, Language language) {
//		this.name = name;
//		this.surname = surname;
//		this.language = language;
//	}
//	
	public int getAuthorId() {
		return authorId;
	}
	
	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public Language getLanguage() {
		return language;
	}
	
	@Override
	public String toString() {
		return name + ", " + surname + ", " + language;
	}
}
