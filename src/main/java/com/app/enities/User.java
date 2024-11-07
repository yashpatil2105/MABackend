package com.app.enities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString(exclude = "password")
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

	@NotBlank(message = "First name can't be blank")
	@Length(min = 4, max = 20, message = "Invalid first name!!!!!!")
	@Column(name = "first_name", length = 20)
	private String firstName;

	@NotBlank(message = "Last name can't be blank")
	@Column(name = "last_name", length = 20)
	private String lastName;

	@Column(length = 30, unique = true)
	@Email(message = "Invalid email format")
	private String email;
	
	@Column(length = 30, unique = true,name = "alternate_email")
	@Email(message = "Invalid email format")
	private String alternateEmail;

	@Column(length = 300)
	@JsonProperty(access = Access.WRITE_ONLY) // skipped during ser(=marshalling) n kept during de-ser (un marshalling)
//	@Pattern(regexp ="((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})",message = "Invalid Password !")
	private String password;
	
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

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	@NotNull(message = "Role must be supplied")
	private Role role;

	@OneToMany(mappedBy = "ob", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<Campaign> campaign = new HashSet<Campaign>();

}
