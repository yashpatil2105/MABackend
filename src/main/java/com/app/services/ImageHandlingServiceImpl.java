package com.app.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.app.customexception.ResourceNotFoundException;
import com.app.dto.ApiResponse;
import com.app.enities.Campaign;
import com.app.repository.CampaignRepository;

@Service
@Transactional
public class ImageHandlingServiceImpl implements ImageHandlingService {
	// To tell SC , evaluate SpEL (spring expr language) n inject it's value -->
	// field
	@Value("${content.upload.folder}")
	private String folderName;
	// dep : dao layer i/f :

	@Autowired
	private CampaignRepository campRepo;

	@PostConstruct
	public void myInit() {
		System.out.println("in myInit " + folderName);
		// chk of folder exists --o.w create one!
		File path = new File(folderName);
		if (!path.exists()) {
			path.mkdirs();
		} else
			System.out.println("folder alrdy exists....");
	}

	@Override
	public ApiResponse uploadImage(Integer campId, MultipartFile imageFile) throws IOException, ResourceNotFoundException {
		// chk if emp exists by the id ?
		Campaign campaign = campRepo.findById(campId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Emp Id : Image Uploading failed!!!!!!!!"));
		// valid emp : PERSISTENT --create complete path to the image
		String targetPath = folderName + File.separator + imageFile.getOriginalFilename();
		System.out.println(targetPath);
		
		// copy image file contents to the specified path
		Files.copy(imageFile.getInputStream(), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
		
		//OR for DB
		/*
		 * Emp entity :Add @Lob private  byte[] contents; emp.setContents(imageFile.getBytes());
		 */
		// => success
		// save image path in DB
		campaign.setImagePath(targetPath);
		return new ApiResponse("Image Uploaded successfully!");
	}

	@Override
	public byte[] serveImage(Integer campId) throws IOException, ResourceNotFoundException {
		// chk if emp exists by the id ?
		Campaign campaign = campRepo.findById(campId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Emp Id : Image Download failed!!!!!!!!"));
		// valid emp : PERSISTENT --create complete path to the image
		String path = campaign.getImagePath();
		if (path == null)
			throw new ResourceNotFoundException("Image does not exist !!!!!");
		//OR to lift it from DB emp.getContents() --> byte[]
		return Files.readAllBytes(Paths.get(path));

	}



}
