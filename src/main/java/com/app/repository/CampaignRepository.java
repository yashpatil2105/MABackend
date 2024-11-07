package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.enities.Campaign;
import com.app.enities.User;

public interface CampaignRepository extends JpaRepository<Campaign, Integer> {
	
	List<Campaign> findByOb(User user);
	
	@Query(value="SELECT * FROM Campaign c WHERE c.active=false",nativeQuery = true)
	List<Campaign> findByInactive();
	
	@Query(value="SELECT * FROM Campaign c WHERE c.active=true",nativeQuery = true)
	List<Campaign> findByActive();
	
	@Modifying
	@Query("DELETE FROM Campaign c WHERE c.id=?1")
	void deleteById(Integer id);
	
	@Modifying 
	@Query("update Campaign c set c.active=true where c.id=?1")
	int updateActive(Integer campid);
	
}
