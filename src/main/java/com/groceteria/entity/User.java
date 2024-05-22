package com.groceteria.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name="User_Table")
public class User {
	
	//USER ID
	@Id
	@SequenceGenerator(name = "generator1",  initialValue = 1000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator1")
	@Column(name="User_Id")
	private Integer userId;
	
	//USER NAME
	@Column(name="first_name" ,length=20)
	@NotEmpty
	@Size(min=3 , message="Name must contain atleast 3 characters")
	private String firstName;
	
	@Column(name="last_name" ,length=20)
	@NotEmpty
	@Size(min=3 , message="Name must contain atleast 3 characters")
	private String lastName;
	
	//DATE OF BIRTH
	@Column(name="DOB",length=30)
	private Date dateOfBirth;
	
	//GENDER
	@Column(name="Gender",length=30)
	@NotEmpty
	@Size(min=4,message="Gender must contain atleast 4 characters")
	private String gender;
	
	//EMAIL ID
	@Column(name="Email_Id",unique = true, length=30)
	@NotEmpty
	public String emailId;
	
	//PASSWORD
	@Column(name="password")
	@NotEmpty
	public String password;
	
	//CONTACT NUMBER
	@Column(name="phone_num",length=30)
	@NotEmpty
	
	private String phoneNumber;
	
	//DISTRICT
	@Column(name="District", length=30)
	@NotEmpty
	
	private String district;
	
	//STATE
	@Column(name="state",length=20)
	@NotEmpty
	
    private String state;
	
	//ADDRESS
	@Column(name="Address",length=30)
	@NotEmpty
	
	private String address;
	
	//ZIPCODE
	@Column(name="Zipcode",length=20)
	@NotEmpty
	
	private String zipcode;
	
	//USER ROLE
	@Column(name="User_Role")
	@NotEmpty
	private String role;

}
