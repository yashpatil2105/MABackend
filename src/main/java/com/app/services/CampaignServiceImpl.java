package com.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.CampaignAdminDto;
import com.app.dto.CampaignDto;
import com.app.enities.Campaign;
import com.app.enities.User;
import com.app.repository.CampaignRepository;
import com.app.repository.UserRepository;
import com.app.utils.ObjectMapperUtils;

@Service
@Transactional
public class CampaignServiceImpl implements CampaignService{
	
	@Autowired
	private CampaignRepository campaignRepo;
	
	@Autowired
	private UserRepository userRepo;
	

	@Override
	public List<CampaignDto> getAllCampaign(){	
		return ObjectMapperUtils.mapAll(campaignRepo.findByActive(),CampaignDto.class);
	}


	@Override
	public Campaign getCampaign(Integer campId) {
		return  campaignRepo.findById(campId).orElseThrow(() -> new UsernameNotFoundException("Invalid Campaign ID"));
	}
	
	@Override
	public List<CampaignAdminDto> deactiveCampaign() {
		return ObjectMapperUtils.mapAll(campaignRepo.findByInactive(),CampaignAdminDto.class);   
		
	}
    
	@Override
	public List<CampaignAdminDto> activeCampaign() {
		
		return ObjectMapperUtils.mapAll(campaignRepo.findByActive(),CampaignAdminDto.class);
	}

	@Override
	public void activeCampaign(Integer campId) {
		System.out.println("activeCampaign "+ campId);
		campaignRepo.updateActive(campId);
	}


	@SuppressWarnings("unused")
	@Override
	public void deleteCampaignId(Integer campId) {
		System.out.println("campid"+campId);
		Campaign camp = campaignRepo.findById(campId).orElseThrow(() -> new UsernameNotFoundException("Invalid Campaign ID"));
		User user = userRepo.findById(camp.getOb().getId()).orElseThrow(() -> new UsernameNotFoundException("Invalid User ID"));
	 
//      camp.setBenificaryUser(null);
//		camp.setBenificaryOther(null);
//		camp.setBenificaryRelative(null);
//		camp.setOb(null);
		campaignRepo.deleteById(camp.getId());
//		Campaign camp = campaignRepo.findById(campId).orElseThrow(() -> new UsernameNotFoundException("Invalid Campaign ID"));
		System.out.println("campid"+campId);
//		campaignRepo.delete(camp);
		System.out.println("camb vpid"+campId);
	}

    
	

}
