package JavaRestMaster.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "basic")
public class Basic 
{
	@Id
	@GeneratedValue
	private int id;
	
	@Column(unique = true)
	private String name;
	
	@OneToOne
	@JsonIgnore
	private User user;

	public Basic() {}

	public Basic(String name, User user) {
		this.name = name;
		this.user = user;
	}

	public Basic(String name) {
		this.name = name;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Basic [id=" + id + ", name=" + name + "]";
	}

}
