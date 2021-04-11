package com.cognizant.drugservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.drugservice.entity.DrugDetails;
import com.cognizant.drugservice.exception.DrugNotFoundException;

@Repository
public interface DrugRepository extends JpaRepository<DrugDetails, Integer> {

	DrugDetails findByNameIgnoreCase(String name) throws DrugNotFoundException;

}
