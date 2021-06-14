package com.trabajo.Grupo16OO22021.entities;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "lastname")
	private String lastname;
	
	private int document;

	@Column(name = "email", unique = true, length = 45)
	private String email;

	@Column(name = "username", unique = true, length = 45)
	private String username;

	@Column(name = "password", unique = true, length = 100)
	private String password;

	@Column(name = "enabled")
	private boolean enabled;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private UserRole userRole;

	public User() {
	}

	public User(int id, String name, String lastname, int document, String email, String username, String password,
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

	public User(String name, String lastname, int document, String email, String username, String password,
			boolean enabled, UserRole userRole) {
		super();
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

	protected void setId(int id) {
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
