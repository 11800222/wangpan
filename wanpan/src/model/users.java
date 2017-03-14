package model;

import java.util.HashSet;
import java.util.Set;

public class users {
	private String username;
	private String password;
	private Set<Files> files = new HashSet<Files>();  //TODO lazy?
	
	
	public Set<Files> getFiles() {
		return files;
	}

	public void setFiles(Set<Files> files) {
		this.files = files;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
