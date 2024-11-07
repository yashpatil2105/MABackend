package com.app.services;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.dto.DonarDto;
import com.app.enities.Campaign;
import com.app.repository.CampaignRepository;
import com.app.utils.ObjectMapperUtils;


@Transactional
@Service
public class DonarServiceImpl implements DonarService {
	
	@Autowired
	private CampaignRepository campaignRepo;
	

	@Override
	public List<DonarDto> getDonarByCampId(Integer campId) {
		Campaign campaign = campaignRepo.findById(campId).orElseThrow(() -> new UsernameNotFoundException("Invalid Campaign ID"));
		return ObjectMapperUtils.mapAll(campaign.getDonars(),DonarDto.class);
	}

}
