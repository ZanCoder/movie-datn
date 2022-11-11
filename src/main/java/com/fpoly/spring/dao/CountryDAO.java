package com.fpoly.spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpoly.spring.model.Country;
import com.fpoly.spring.model.Genre;

@Repository
public interface CountryDAO extends JpaRepository<Country, Integer>{
	@Query(value="select top 10 * from country", nativeQuery=true)
	List<Country> findCountryByTop10();
	
	@Query(value="SELECT o FROM Country o WHERE o.country_slug like ?1")
	Country findBySlugLike(String slug);
}
