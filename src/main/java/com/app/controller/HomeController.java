package com.app.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.app.customexception.ResourceNotFoundException;
import com.app.dto.CampaignDto;
import com.app.enities.Campaign;
import com.app.services.CampaignService;
import com.app.services.ImageHandlingService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class HomeController {
	
	@Autowired
	private CampaignService campaignService;
	
	@Autowired
	private ImageHandlingService imageService;
	
	@GetMapping("/campaign")
	public List<CampaignDto> showAllCampaign() {
		return campaignService.getAllCampaign();
	}
	
	   @GetMapping("/campaign/{campId}")
	   public Campaign showCampaign(@PathVariable Integer campId) {
		   return campaignService.getCampaign(campId);
	   }
	   
	   
	   
	@GetMapping(value = "/{campId}/image", produces = { MediaType.IMAGE_GIF_VALUE,
			MediaType.IMAGE_JPEG_VALUE,
			MediaType.IMAGE_PNG_VALUE })
	public ResponseEntity<?> serveImageFromServerSideFolder(@PathVariable Integer campId) throws IOException, ResourceNotFoundException {
		System.out.println("in serve img " + campId);
		return new ResponseEntity<>(imageService.serveImage(campId), HttpStatus.OK);
	}

}
