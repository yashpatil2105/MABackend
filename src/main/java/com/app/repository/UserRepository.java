package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.enities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);
	
	@Modifying //For DML
	@Query("update User u set u.firstName=?1,u.lastName=?2,u.email=?3,u.alternateEmail=?4,u.mobileNo=?5 ,u.address=?6,u.city=?7,u.country=?8,u.state=?9,u.pincode=?10 where u.id=?11")
	int updateProfile(String firstName,String lastName,String email,String alternateEmail,String mobileNo,String address, String city, String country, String state,String pincode,Integer id);
	
	
    

}
