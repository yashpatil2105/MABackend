package com.app.dto;

import java.time.LocalDate;

import com.app.enities.BenificaryOther;
import com.app.enities.BenificaryRelative;
import com.app.enities.BenificaryUser;
import com.app.enities.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CampaignAdminDto {
	private Integer id;
    private String createdBy;
    private String name;
    private String email;
    private String title;
    private double amount;
    private LocalDate endDate;
    private String ailment;
    private User ob;
    private BenificaryUser benificaryUser;
    private BenificaryRelative benificaryRelative;
    private BenificaryOther benificaryOther;
}
