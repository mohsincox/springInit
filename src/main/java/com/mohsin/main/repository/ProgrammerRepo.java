package com.mohsin.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mohsin.main.model.Programmer;

@Repository
public interface ProgrammerRepo extends JpaRepository<Programmer, Integer> {

	List<Programmer> findBypLang(String pLang);
	
	@Query("from Programmer where pName =?1")
	List<Programmer> findP(String pName);
}
