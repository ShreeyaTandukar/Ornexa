package com.Ornexa.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
	private int id;
	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	private String phoneNumber;
	private Date Dob;
	private String password;
	private String image;
	private String role;
	private String Status;


	// Default Constructor
    public User() {}
    
    public User(String userName,String password) {
    	this.userName=userName;
    	this.password=password;
    }

    // Parameterized Constructor
    public User(int id,String firstName, String lastName, String userName, String email, 
                    String phoneNumber, Date dob, String password, String image) {
    	this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.Dob = dob;
        this.password = password;
        this.image = image;
        this.role="customer";
        this.Status="pending";
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName=firstName;
		
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName=lastName;
		
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName=userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email=email;
		
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber=phoneNumber;
		
	}
	public Date getDob() {
		return Dob;
	}
	public void setDob(Date dob) {
		this.Dob=dob;
		
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password=password;
		
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image=image;
		
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role=role;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String Status) {
		this.Status=Status;
	}	
}
	
