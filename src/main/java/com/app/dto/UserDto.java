package com.app.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	@JsonProperty(access = Access.READ_ONLY) // user id will be serialized n sent to clnt BUT it won't be read from clnt
	// n de-serialized
private Integer userId;
	 
	private Integer id;
	
	@NotBlank(message = "First name can't be blank")
	private String firstName;

	@NotBlank(message = "Last name can't be blank")
	private String lastName;
    
	@NotBlank(message = "Email can't be blank")
	@Email(message = "Invalid email format")
	private String email;

	
	@Column(length = 30, unique = true,name = "alternate_email")
	@Email(message = "Invalid email format")
	private String alternateEmail;

	
	@Column(name = "mobile_no", length = 20)
	private String mobileNo;

	@Column( length = 200)
	private String address;
	
	@Column( length = 50)
	private String city;
	
	@Column( length = 50)
	private String state;
	
	@Column( length = 50)
	private String country;
	
	@Column( length = 50)
	private String pincode;



}
