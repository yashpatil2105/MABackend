package com.app.services;

import java.util.Set;

import org.springframework.security.core.Authentication;

import com.app.customexception.ResourceNotFoundException;
import com.app.enities.BenificaryOther;
import com.app.enities.BenificaryRelative;
import com.app.enities.BenificaryUser;
import com.app.enities.Campaign;
import com.app.enities.User;

public interface UserService {

	User addNewUser(User user);

	BenificaryUser addNewUserBenificary(Integer campId,BenificaryUser buser);

	BenificaryRelative addNewRelativeBenificary(Integer campId,BenificaryRelative relative);

	BenificaryOther addNewOtherBenificary(Integer campId, BenificaryOther other);

	Campaign addNewcampaign(Authentication auth , Campaign campaign);

	User getUser(String email);

	Set<Campaign> getCampaigns(String email);

	int updateUser(User detachedUser) throws ResourceNotFoundException;

	Campaign getCampaign(Integer campId);

	User getUserById(Integer id);

	
}
