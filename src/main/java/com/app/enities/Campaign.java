package com.app.enities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



//@NamedNativeQuery(name = "ChessPlayer.findPlayerNameDtoById_Named",
//query = "SELECT p.first_name as first, p.last_name as last FROM Chess_Player p WHERE id = :id",
//resultSetMapping = "Mapping.PlayerNameDto")
//@SqlResultSetMapping(name = "Mapping.PlayerNameDto",
//   classes = @ConstructorResult(targetClass = CampaignAdminDto.class,
//                                columns = {@ColumnResult(name = "first"),
//                                           @ColumnResult(name = "last")}))
@Entity
@Getter
@Setter
@ToString
public class Campaign extends BaseEntity {

	private double amount;

	@Column(name = "current_amount")
	private double currentAmount;

	@Column(name = "end_date")
	private LocalDate endDate;

	@Column(length = 300)
	private String title;
    
	@Value("${some.key:false}")
	private boolean active;

	@Column(name = "hospital_name", length = 100)
	private String hospitalName;

	@Column(name = "city_of_hospital", length = 50)
	private String cityOfHospital;

	@Column(length = 100)
	private String ailment;


	
	@Column(name = "image_path")
	private String imagePath;
	
	@Column(length = 1000)
	private String story;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			  name = "campaign_donar", 
			  joinColumns = @JoinColumn(name = "campaign_id"), 
			  inverseJoinColumns = @JoinColumn(name = "donar_id"))
	private Set<Donar> donars = new HashSet<Donar>();
    
	
	@Column(name = "no_Of_Donar")
	private int noOfDonar=donars.size();
	
	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "user_id")
	private User ob;

	@OneToOne(mappedBy = "campaign", cascade = CascadeType.ALL,orphanRemoval = true)
	private BenificaryUser benificaryUser;

	@OneToOne(mappedBy = "campaign", cascade = CascadeType.ALL,  orphanRemoval = true)
	private BenificaryRelative benificaryRelative;

	@OneToOne(mappedBy = "campaign",cascade = CascadeType.ALL, orphanRemoval = true)
	private BenificaryOther benificaryOther;
	
//	public void setDonars(Set<Donar> donars) {
//	    this.donars = donars;
//	  }  
}
