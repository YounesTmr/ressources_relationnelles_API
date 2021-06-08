package fr.cesi.cubes.resourceRelationnelles.request.member;

import com.sun.istack.NotNull;

public class LoginForm {
	@NotNull
	private String username;
	
	@NotNull
	private String password;

	public LoginForm() {
	}

	public LoginForm( String username,String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}

	
}
