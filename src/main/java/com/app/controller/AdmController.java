package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CampaignAdminDto;
import com.app.services.CampaignService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin")
public class AdmController {
	
	@Autowired
	private CampaignService campaignService;
	
	@GetMapping("/deactive/campaign")
	public List<CampaignAdminDto> showDeactiveCampaign() {
		return campaignService.deactiveCampaign();
	}
	
	@GetMapping("/active/campaign")
	public List<CampaignAdminDto> showActiveCampaign() {
		return campaignService.activeCampaign();
	}
	
	@GetMapping("/activate/campaign/{campId}")
	public void activateCampaignById(@PathVariable Integer campId) {
		 campaignService.activeCampaign(campId);
	}
	
	@DeleteMapping("/campaign/{campId}")
	public void deleteCampaignbyId(@PathVariable Integer campId) {
		System.out.println(campId);
		 campaignService.deleteCampaignId(campId);
	}
	
}