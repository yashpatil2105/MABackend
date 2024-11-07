package com.app.enities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "benificary_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BenificaryUser extends BaseEntity{
	
	@Column(length = 50)
	private String name;
	
	private int age;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "campaign_id")
	private Campaign campaign;

}
