package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.enities.BenificaryUser;

public interface BenificaryUserRepository extends JpaRepository<BenificaryUser, Integer> {
   
}
