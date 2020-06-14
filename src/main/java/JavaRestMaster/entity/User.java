package JavaRestMaster.entity;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class User 
{
	@Id
	@GeneratedValue
	private int id;
	
	@Column(unique = true)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Column
	private String[] roles;

	public User() {}

	public User(String username, String password, String... roles) {
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	public int getId() {
		return id;
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

	public void setRoles(String[] roles) {
		this.roles = roles;
	}
	
	public String[] getRoles() {
		return this.roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", roles=" + Arrays.toString(roles) + "]";
	}
}
