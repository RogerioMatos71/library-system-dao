package model.user;

import java.util.Objects;

public class User {
	
	private Integer id;
	private String name;
	private String cpf;
	private String email;
	
	public User() {
		
	}
	
	
	public User(Integer id, String name, String cpf, String email) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.email = email;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	} 
	
	
	
	
	

}
