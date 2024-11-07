package com.app.services;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.customexception.ResourceNotFoundException;
import com.app.enities.BenificaryOther;
import com.app.enities.BenificaryRelative;
import com.app.enities.BenificaryUser;
import com.app.enities.Campaign;
import com.app.enities.User;
import com.app.repository.BenificaryOtherRepository;
import com.app.repository.BenificaryRelativeRepository;
import com.app.repository.BenificaryUserRepository;
import com.app.repository.CampaignRepository;
import com.app.repository.UserRepository;
@Service
@Transactional
public class UserServiceImpl implements UserService {
	
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private BenificaryUserRepository bUserRepo;
    
    @Autowired
    private BenificaryRelativeRepository bRelativeRepo;
    
    @Autowired
    private BenificaryOtherRepository bOtherRepo;
    
    @Autowired
    private CampaignRepository campaignRepo;
    
    @Autowired
    private PasswordEncoder enc;
    

	@Override
	public User addNewUser(User user) {
		user.setPassword(enc.encode(user.getPassword()));
		return userRepo.save(user);
	}
	
	@Override
	public User getUser(String email) {
		 User user = userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Invalid Email ID"));
		 return user;
	}
	
	@Override
	public Campaign addNewcampaign(Authentication auth ,Campaign campaign) {
		User user = userRepo.findByEmail(((String)auth.getPrincipal())).orElseThrow(() -> new UsernameNotFoundException("Invalid Email ID"));
		campaign.setOb(user);
//		userRepo.save(user);
		return campaignRepo.save(campaign);
	}
	
	@Override
	public Set<Campaign> getCampaigns(String email) {
		 User user = userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Invalid Email ID"));
		return user.getCampaign();
	}
	
	
	@Override
	public BenificaryUser addNewUserBenificary(Integer campId,BenificaryUser buser) {
		Campaign campaign = campaignRepo.findById(campId).orElseThrow(() -> new UsernameNotFoundException("Invalid Campaign ID"));
		buser.setCampaign(campaign);
		return bUserRepo.save(buser);
	}
	
	@Override
	public BenificaryRelative addNewRelativeBenificary(Integer campId, BenificaryRelative relative) {
		Campaign campaign = campaignRepo.findById(campId).orElseThrow(() -> new UsernameNotFoundException("Invalid Campaign ID"));
		relative.setCampaign(campaign);
		return bRelativeRepo.save(relative);
		
	}

	@Override
	public BenificaryOther addNewOtherBenificary(Integer campId, BenificaryOther other) {
		Campaign campaign = campaignRepo.findById(campId).orElseThrow(() -> new UsernameNotFoundException("Invalid Campaign ID"));
		other.setCampaign(campaign);
		return bOtherRepo.save(other);
	}


	@Override
	public int updateUser(User dUser) throws ResourceNotFoundException {
		if (userRepo.existsById(dUser.getId())) {
			//String firstName,String lastName,String email,String alternateEmail,String mobileNo,String address, String city, String country, String state,String pincode,Integer id
			return userRepo.updateProfile(dUser.getFirstName(),dUser.getLastName(),dUser.getEmail(),dUser.getAlternateEmail(),dUser.getMobileNo(),dUser.getAddress(),dUser.getCity(),dUser.getCountry(),dUser.getState(),dUser.getPincode(),dUser.getId());
		}
		throw new ResourceNotFoundException("Invalid Emp Id : Updation Failed!!!!!!!!");
		
	}

	@Override
	public Campaign getCampaign(Integer campId) {
		return null;
	}

	@Override
	public User getUserById(Integer id) {
		return userRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("Invalid User ID"));
	}




	


}
