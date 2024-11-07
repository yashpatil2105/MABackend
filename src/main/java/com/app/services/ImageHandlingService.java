package com.app.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.app.customexception.ResourceNotFoundException;
import com.app.dto.ApiResponse;

public interface ImageHandlingService {

	ApiResponse uploadImage(Integer campId, MultipartFile imageFile) throws IOException, ResourceNotFoundException;
	byte[] serveImage(Integer campId) throws IOException, ResourceNotFoundException;

}
