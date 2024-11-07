package com.app.services;

import java.util.List;

import com.app.dto.DonarDto;

public interface DonarService {
	
	List<DonarDto> getDonarByCampId(Integer campId);

}
