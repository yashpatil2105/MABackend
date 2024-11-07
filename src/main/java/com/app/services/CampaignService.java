package com.app.services;

import java.util.List;

import com.app.dto.CampaignAdminDto;
import com.app.dto.CampaignDto;
import com.app.enities.Campaign;


public interface CampaignService {
	
	List<CampaignDto> getAllCampaign();

	Campaign getCampaign(Integer campId);

	 List<CampaignAdminDto> deactiveCampaign();

	void activeCampaign(Integer campId);

	List<CampaignAdminDto> activeCampaign();

	void deleteCampaignId(Integer campId);

}
