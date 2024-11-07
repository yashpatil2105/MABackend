package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.DonarDto;
import com.app.services.DonarService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/donar")
public class DonarController {
	
	@Autowired
	private DonarService donarService;
	
	@GetMapping("/{campId}/campaign")
	public List<DonarDto> getDonarCampId(@PathVariable Integer campId){
		return donarService.getDonarByCampId(campId);
	}
	
	

}
