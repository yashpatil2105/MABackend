package com.app.controller;

import java.io.IOException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.customexception.ResourceNotFoundException;
import com.app.enities.BenificaryOther;
import com.app.enities.BenificaryRelative;
import com.app.enities.BenificaryUser;
import com.app.enities.Campaign;
import com.app.enities.User;
import com.app.services.ImageHandlingService;
import com.app.services.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
public class UserController {
	
	   @Autowired
	   private UserService userService;
	   
	   @Autowired
	   private ImageHandlingService imageService;
	   
	   
//	   @PostMapping("/signup")
//	   public User addUser(@RequestBody User user){
//		   return userService.addNewUser(user);
//	   }
	   
	   @GetMapping("/profile")
	   public User showUser(Authentication auth) {
		   System.out.println("Auth : "+ auth.getPrincipal());
		   return userService.getUser((String) auth.getPrincipal());
	   }
	   
	   @GetMapping("/profile/{id}")
	   public User showUser(Integer id) {
		   return userService.getUserById(id);
	   }
	   
	   @PutMapping("/profile")
	   public int updateUserProfile(@RequestBody User detachedUser) throws ResourceNotFoundException {
		  // System.out.println("Auth : "+ auth.getPrincipal());
		   return userService.updateUser(detachedUser);
	   }
	   
	   
	   @PostMapping("/benificary/user/{campId}")
	   public BenificaryUser addUserBenificary(@PathVariable Integer campId,@RequestBody BenificaryUser buser){
		   return userService.addNewUserBenificary(campId,buser);
	   }
	   
	   @PostMapping("/benificary/relative/{campId}")
	   public BenificaryRelative addRelativeBenificary(@PathVariable Integer campId,@RequestBody BenificaryRelative relative){
		  
		   return userService.addNewRelativeBenificary(campId,relative);
	   }
	   
	   @PostMapping("/benificary/other/{campId}")
	   public BenificaryOther addOtherBenificary(@PathVariable Integer campId,@RequestBody BenificaryOther other){
		   return userService.addNewOtherBenificary(campId,other);
	   }
	   
	   @GetMapping("/campaign")
	   public Set<Campaign> showCampaigns(Authentication auth) {
		   System.out.println("Auth : "+ auth.getPrincipal());
		   return userService.getCampaigns((String) auth.getPrincipal());
	   }
	   

	   
	   @PostMapping("/campaign")
	   public Campaign addCampaign(Authentication auth, @RequestBody Campaign campaign){
		   return userService.addNewcampaign(auth ,campaign);
		  
	   }
	   
	// Add REST end point to upload image
		// URL : http://host:port/employees/{empId}/image , Method=POST
		@PostMapping(value="/{campId}/image", consumes = "multipart/form-data")
		public ResponseEntity<?> uploadImageToServerSideFolder(@RequestParam MultipartFile imageFile,
				@PathVariable Integer campId
				) throws IOException, ResourceNotFoundException {
			System.out.println("in upload img " + campId + " " + imageFile.getOriginalFilename());
			return new ResponseEntity<>(imageService.uploadImage(campId, imageFile), HttpStatus.CREATED);
		}

		// Add REST end point to download/serve image , method=GET
		// URL : http://host:port/employees/{empId}/image
		@GetMapping(value = "/{campId}/image", produces = { MediaType.IMAGE_GIF_VALUE, 
				MediaType.IMAGE_JPEG_VALUE,
				MediaType.IMAGE_PNG_VALUE })
		public ResponseEntity<?> serveImageFromServerSideFolder(@PathVariable Integer campId) throws IOException, ResourceNotFoundException {
			System.out.println("in serve img " + campId);
			return new ResponseEntity<>(imageService.serveImage(campId), HttpStatus.OK);
		}

}
