package JavaRestMaster.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "basic")
public class Basic 
{
	@Id
	@GeneratedValue
	private int id;
	
	@Column(unique = true)
	private String name;

	public Basic() {}

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

	@Override
	public String toString() {
		return "Basic [id=" + id + ", name=" + name + "]";
	}
	
}
