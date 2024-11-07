package com.app.enities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "donar")
@Getter
@Setter
@ToString(exclude = "campaigns")
public class Donar extends BaseEntity{
	
	
	@NotBlank(message = "First can't be blank")
	@Column(length = 45)
	private String name;
	
	@NotBlank(message = "Email can't be blank")
	@Email
	private String email;
	
	@NotBlank(message = "mobile no can't be blank")
	private String mobileNo;
	
	@Column(length = 200)
    private String razorpay_order_id;
	
	@Column(length = 200)
	private String razorpay_payment_id;

	private double amount;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "donars")
	private Set<Campaign> campaigns = new HashSet<Campaign>();


//	public void setCampaigns(Campaign campaign) {
//		this.campaigns.add(campaign);
//	}
	

	
	

}
