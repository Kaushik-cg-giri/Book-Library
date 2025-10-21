package com.book_management.entity;

import org.hibernate.annotations.GeneratorType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int  usersId;
	
	private String usersName;
	
	private String usersPswrd;
	
	private String usersRole;
	
	

	public Users() {
		super();
	}

	
	public Users(int usersId, String usersName, String usersPswrd, String usersRole) {
		super();
		this.usersId = usersId;
		this.usersName = usersName;
		this.usersPswrd = usersPswrd;
		this.usersRole = usersRole;
	}

	

	public int getusersId() {
		return usersId;
	}


	public void setusersId(int usersId) {
		this.usersId = usersId;
	}


	public String getusersName() {
		return usersName;
	}

	public void setusersName(String usersName) {
		this.usersName = usersName;
	}

	public String getusersPswrd() {
		return usersPswrd;
	}

	public void setusersPswrd(String usersPswrd) {
		this.usersPswrd = usersPswrd;
	}

	public String getusersRole() {
		return usersRole;
	}

	public void setusersRole(String usersRole) {
		this.usersRole = usersRole;
	}
	
	

}
