package com.trabajo.Grupo16OO22021.models;



import com.trabajo.Grupo16OO22021.entities.UserRole;

public class UserModel {
	private int id;
	private String name;
	private String lastname;
	private int document;
	private String email;
	private String username;
	private String password;
	private boolean enabled;
	private UserRole userRole;

	public UserModel() {
	}

	public UserModel(int id, String name, String lastname, int document, String email, String username, String password,
			boolean enabled, UserRole userRole) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.document = document;
		this.email = email;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.userRole = userRole;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getDocument() {
		return document;
	}

	public void setDocument(int document) {
		this.document = document;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

}
