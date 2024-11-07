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

@Entity
@Table(name = "benificary_relative")
@Getter
@Setter
public class BenificaryRelative extends BaseEntity {
	
	@Column(name = "relative_name", length = 100)
	private String relativeName;
	
	@Column(name = "relation", length = 100)
	private String relation;
	
	private int age;
	
	@Column(length = 10)
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Column(length = 350)
	private String address;
	
	@Column(length = 50)
	private String city;
	
	@Column(name = "mobile_no", length = 20)
	private String benificaryMobile;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "campaign_id")
	private Campaign campaign;
	
}
