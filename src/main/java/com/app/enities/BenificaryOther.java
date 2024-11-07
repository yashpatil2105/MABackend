package com.app.enities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "benificary_other")
@Getter
@Setter
@ToString
public class BenificaryOther extends BaseEntity {
	
	@Column(name = "name", length = 30)
	private String name;
	
	private int age;
	
	@Column(length = 10)
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	
	@Column(length = 350)
	private String address;
	
	@Column(length = 50)
	private String city;
	
	@Column(name = "mobile_no", length = 20)
	private String mobileNo;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "campaign_id")
	private Campaign campaign;

}
